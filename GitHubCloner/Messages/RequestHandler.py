import os.path

from GithubCloner.cloneLogic import clone
from Model.CloningRequest import CloningRequest
from Model.CloningResult import CloningResult
from configuration import PATH


class RequestHandler:

    def __init__(self,producer):
        self.feedbackProducer = producer

    def handleRequest(self,cloningRequest):
        cloningRequest = CloningRequest.fromJson(cloningRequest)
        try:
            self.feedbackProducer.start(cloningRequest)
            repositoryName = "{}-{}".format(*authorAndRepoFromOrigin(cloningRequest.Origin))
            dir = os.path.join(PATH,repositoryName)
            os.mkdir(dir)
            clone(cloningRequest.Origin, dir)
            result = CloningResult(repositoryName)
            self.feedbackProducer.success(result,cloningRequest.Origin)
        except Exception as e:
            print(e)
            result = CloningResult("")
            self.feedbackProducer.failure(result,cloningRequest.Origin)


def authorAndRepoFromOrigin(origin):
    author = origin.split("https://github.com/")[1].split("/")[0]
    repository = origin.split("https://github.com/")[1].split("/")[1].split(".git")[0]
    return author,repository