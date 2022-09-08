import os
import shutil

from Model.ReleasesDeleteRequest import ReleasesDeleteRequest
from Model.ReleasesDeleterResult import ReleasesDeleterResult

from configuration import PATH


class RequestHandler:

    def __init__(self, producer):
        self.feedbackProducer = producer

    def handleRequest(self, releasesDeleteRequest):
        try:
            releasesDeleteRequest = ReleasesDeleteRequest.fromJson(releasesDeleteRequest)

            fullReleasesFilePath = os.path.join(PATH,releasesDeleteRequest.ReleasesPath)

            shutil.rmtree(fullReleasesFilePath,onerror= onerror)

            requestResult = ReleasesDeleterResult(True)

            self.feedbackProducer.success(requestResult, origin=releasesDeleteRequest.RepositoryOrigin)

        except Exception as e:
            print(e)
            requestResult = ReleasesDeleterResult(True)
            self.feedbackProducer.failure(requestResult, origin=releasesDeleteRequest.RepositoryOrigin)


def onerror(func, path, exc_info):
    """
    Error handler for ``shutil.rmtree``.

    If the error is due to an access error (read only file)
    it attempts to add write permission and then retries.

    If the error is for another reason it re-raises the error.

    Usage : ``shutil.rmtree(path, onerror=onerror)``
    """
    import stat
    # Is the error an access error?
    if not os.access(path, os.W_OK):
        os.chmod(path, stat.S_IWUSR)
        func(path)
    else:
        raise