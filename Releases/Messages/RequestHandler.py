from Model.ReleaseRequest import  ReleaseRequest
from Model.ReleaseResult import ReleaseResult
from ReleaseLogic import getReleases


class RequestHandler:

    def __init__(self,producer):
        self.feedbackProducer = producer

    def handleRequest(self,releaseRequest):
        releaseRequest = ReleaseRequest.fromJson(releaseRequest)
        try:
            commits = getReleases(releaseRequest.RepositoryOrigin)
            commits =  list(dict.fromkeys(commits))
            result = ReleaseResult(commits)
            self.feedbackProducer.success(result,origin=releaseRequest.RepositoryOrigin)
        except Exception as e:
            print(e)
            self.feedbackProducer.failure(ReleaseResult([]),origin=releaseRequest.RepositoryOrigin)