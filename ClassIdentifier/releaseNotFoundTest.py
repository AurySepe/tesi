from pydriller import Repository
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

PathToRepo = "C:/Users/aurel/Desktop/TestSource/libgdx"

repo = Repository(PathToRepo)
numberOfModifedFiles = 0
for commit in repo.traverse_commits():
    for modifedFile in commit.modified_files:
        print(modifedFile.filename)
        numberOfModifedFiles += 1

print(numberOfModifedFiles)


