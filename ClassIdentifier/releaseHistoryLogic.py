import json
import os
import javalang

from pydriller import Repository, ModificationType

def traverse_commits(self):
    """
    Analyze all the specified commits (all of them by default), returning
    a generator of commits.
    """
    for path_repo in self._conf.get('path_to_repos'):
        with self._prep_repo(path_repo=path_repo) as git:

            # Get the commits that modified the filepath. In this case, we can not use
            # git rev-list since it doesn't have the option --follow, necessary to follow
            # the renames. Hence, we manually call git log instead
            if self._conf.get('filepath') is not None:
                self._conf.set_value(
                    'filepath_commits',
                    git.get_commits_modified_file(self._conf.get('filepath'),
                                                  self._conf.get('include_deleted_files'))
                )

            # Gets only the commits that are tagged
            if self._conf.get('only_releases'):
                self._conf.set_value('tagged_commits', git.get_tagged_commits())

            # Build the arguments to pass to git rev-list.
            rev, kwargs = self._conf.build_args()

            for commit in git.get_list_commits(rev, **kwargs):
                yield commit




Repository.traverse_commits = traverse_commits


def isJava(fileName):
    splittedFilexName = fileName.split(".")
    if len(splittedFilexName) != 0:
        extension = splittedFilexName[-1]
        return extension == "java"
    else:
        return False


def getJavaPackageName(source_code):
    fullName = ""
    try:
        tokens = javalang.tokenizer.tokenize(source_code)
        for token in tokens:
            if token.value == "package":
                break
        for token in tokens:
            if token.value == ";":
                break
            fullName += token.value
        return fullName
    except Exception as e:
        return ""


def getJavaFullName(source_code, filename):
    packageName = getJavaPackageName(source_code)
    className = filename.split(".")[0]
    return f"{packageName}.{className}"


def getClassesHistory(repoPath, outputDir, releases, releaseOutputFile):
    repo = Repository(repoPath)

    outputDict = {}
    newID = 1
    reverseDict = {}
    hashes = set(releases)
    for commit in repo.traverse_commits():
        for modifiedFile in commit.modified_files:
            if isJava(modifiedFile.filename):
                if modifiedFile.change_type == ModificationType.ADD:
                    fullName = getJavaFullName(modifiedFile.source_code, modifiedFile.filename)
                    if fullName == "":
                        continue
                    if fullName not in reverseDict.keys():
                        outputDict[newID] = fullName
                        reverseDict[fullName] = newID
                        newID += 1
                elif modifiedFile.change_type == ModificationType.DELETE:
                    fullName = getJavaFullName(modifiedFile.source_code_before, os.path.basename(modifiedFile.old_path))
                    if fullName == "":
                        continue
                    if fullName in reverseDict.keys():
                        id = reverseDict[fullName]
                        del outputDict[id]
                        del reverseDict[fullName]
                elif (
                        modifiedFile.change_type == ModificationType.RENAME or modifiedFile.change_type == ModificationType.MODIFY) and modifiedFile.source_code_before is not None:

                    fullNameBefore = getJavaFullName(modifiedFile.source_code_before,
                                                     os.path.basename(modifiedFile.old_path))
                    fullNameNow = getJavaFullName(modifiedFile.source_code, modifiedFile.filename)
                    if fullNameBefore not in reverseDict.keys() and fullNameNow not in reverseDict.keys():
                        outputDict[newID] = fullNameNow
                        reverseDict[fullNameNow] = newID
                        newID += 1
                    elif fullNameBefore in reverseDict.keys():
                        id = reverseDict[fullNameBefore]
                        outputDict[id] = fullNameNow
                        del reverseDict[fullNameBefore]
                        reverseDict[fullNameNow] = id
        if commit.hash in hashes:
            print(commit.hash)
            filePath = os.path.join(outputDir, f"{commit.hash}.json")
            f = open(filePath, "w")
            json.dump(reverseDict, f, sort_keys=True, indent=4, separators=(',', ': '))
            f.close()
            releaseFile = open(releaseOutputFile, "a")
            releaseFile.write(f'{commit.hash}\n')
            releaseFile.close()

