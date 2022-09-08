import os

from Model.ClassHistoryRequest import ClassHistoryRequest
from Model.ClassHistoryResult import ClassHistoryResult

from configuration import PATH
from releaseHistoryLogic import getClassesHistory


class RequestHandler:

    def __init__(self, producer):
        self.feedbackProducer = producer

    def handleRequest(self, classHistoryRequest):
        try:
            classHistoryRequest = ClassHistoryRequest.fromJson(classHistoryRequest)

            relativeReleasesFilePath = os.path.join(classHistoryRequest.RepositoryName,"successfulReleases.txt")
            fullReleasesFilePath = os.path.join(PATH,relativeReleasesFilePath)

            classHistoryRelativePath = os.path.join(classHistoryRequest.RepositoryName,"CLASSHISTORY")

            classHistoryPath = os.path.join(PATH,classHistoryRelativePath)
            os.mkdir(classHistoryPath)

            repositoryPath = os.path.join(PATH,classHistoryRequest.RepositoryClonePath)

            getClassesHistory(repositoryPath,classHistoryPath,classHistoryRequest.Releases,fullReleasesFilePath)


            requestResult = ClassHistoryResult(classHistoryRelativePath,relativeReleasesFilePath)

            self.feedbackProducer.success(requestResult, origin=classHistoryRequest.RepositoryOrigin)

        except Exception as e:
            print(e)
            requestResult = ClassHistoryResult("", "")
            self.feedbackProducer.failure(requestResult, origin=classHistoryRequest.RepositoryOrigin)

