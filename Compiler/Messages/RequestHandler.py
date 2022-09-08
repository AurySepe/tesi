import concurrent
import os
import shutil

from Model.CompileRequest import CompileRequest
from Model.CompileResult import CompileResult
import concurrent.futures

from configuration import PATH
from tempDirectoryTest import compileRelease


class RequestHandler:

    def __init__(self, producer):
        self.feedbackProducer = producer

    def handleRequest(self, compileRequest):
        try:
            compileRequest = CompileRequest.fromJson(compileRequest)
            repDir = os.path.join(PATH, compileRequest.RepositoryName)

            logPath = os.path.join(repDir, "LOGS")
            os.mkdir(logPath)

            logCompilePath = os.path.join(logPath,"COMPILE")
            os.mkdir(logCompilePath)

            buildPath = os.path.join(repDir, "BUILD")
            os.mkdir(buildPath)

            releasesPath = os.path.join(PATH, compileRequest.ReleasesPath)

            pathOfReleasesAndLogsAndBuilds = getPathsOfReleases(releasesPath, logCompilePath, buildPath)
            with concurrent.futures.ThreadPoolExecutor( max_workers=1) as executor:
                results = executor.map(compileWrapper, pathOfReleasesAndLogsAndBuilds)
            compileSuccessRelativeFilePath = os.path.join(compileRequest.RepositoryName,"releases_compiled_with,success.txt")
            compiledSuccessFilePath = os.path.join(PATH,compileSuccessRelativeFilePath)
            compiledSuccessFile = open(compiledSuccessFilePath,"w")
            for release, result in results:
                if result:
                    compiledSuccessFile.write(f"{release}\n")

            requestResult = CompileResult(compileSuccessRelativeFilePath,os.path.join(compileRequest.RepositoryName, "BUILD"))
            self.feedbackProducer.success(requestResult, origin=compileRequest.RepositoryOrigin)

        except Exception as e:
            print(e)
            self.feedbackProducer.failure(CompileResult("",""), origin=compileRequest.RepositoryOrigin)


def compileWrapper(t):
    return compileReleaseAndGetResult(*t)

def compileReleaseAndGetResult(pathOfRelease: str, pathOflogs, pathOfBuild):
    release = pathOfRelease.split("/")[-1]
    result = True
    try:
        compileRelease(pathOfRelease, pathOflogs)
        extractBuild(pathOfRelease, pathOfBuild)
    except Exception as e:
        print(e)
        result = False
    return (release, result)


def getPathsOfReleases(releasesPath, logPath, buildPath):
    result = []
    root, dirs, files = next(os.walk(releasesPath))
    for release in dirs:
        result.append(
            (os.path.join(releasesPath, release), os.path.join(logPath, release), os.path.join(buildPath, release)))
    return result


def extractBuild(pathOfRelease, pathOfBuild):
    paths = findCompiledClasses(pathOfRelease)
    for path, name in paths:
        pathToCopy = os.path.join(pathOfBuild,name)
        shutil.copytree(path, pathToCopy)

def findCompiledClasses(pathOfInput):
    paths = []
    for root, dirs, files in os.walk(pathOfInput):
        if "build" in dirs and any([".gradle" in s for s in files]):
            classesPath = os.path.join(root, "build")
            name = os.path.basename(root)
            if os.path.isdir(classesPath):
                paths.append((classesPath,name))
    return paths
