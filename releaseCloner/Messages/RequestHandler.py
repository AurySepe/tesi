import os
import shutil
import tempfile

from Model.ReleasesCloneRequest import ReleasesCloneRequest
from Model.ReleasesCloneResult import ReleaseCloneResult
from pydriller import Git

from configuration import PATH


class RequestHandler:

    def __init__(self,producer):
        self.feedbackProducer = producer

    def handleRequest(self,releaseCloningRequest):
        releaseCloningRequest = ReleasesCloneRequest.fromJson(releaseCloningRequest)
        tempdir = tempfile.mkdtemp()
        try:
            #copy in temp
            #per ogni release fai il checkout e copiala nel path
            nameOfRepo = releaseCloningRequest.RepositoryActualName
            pathToRepo = os.path.join(PATH,releaseCloningRequest.RepositoryName,nameOfRepo)
            copyDirectory(pathToRepo,tempdir)
            temRep = os.path.join(tempdir,nameOfRepo)
            releasesDir = os.path.join(PATH,releaseCloningRequest.RepositoryName,"RELEASES")
            os.mkdir(releasesDir)
            rep = Git(temRep)
            releasesFilePath = os.path.join(PATH,releaseCloningRequest.ReleasesFilePath)
            releasesFile = open(releasesFilePath,"r")
            releases = releasesFile.read().splitlines()
            releasesFile.close()
            for release in releases:
                rep.checkout(release)
                copyDirectory(temRep,releasesDir,release)

            result = ReleaseCloneResult(os.path.join(releaseCloningRequest.RepositoryName,"RELEASES"))

            self.feedbackProducer.success(result,origin=releaseCloningRequest.RepositoryOrigin)

        except Exception as e:
            print(e)
            self.feedbackProducer.failure(ReleaseCloneResult(""),origin=releaseCloningRequest.RepositoryOrigin)
        finally:
            shutil.rmtree(tempdir,onerror=onerror)



def copyDirectory(dirFrom, dirTo, newName=None):
    if newName is None:
        dest_dir = os.path.join(dirTo, os.path.basename(dirFrom))
    else:
        dest_dir = os.path.join(dirTo, os.path.basename(newName))
    shutil.copytree(dirFrom, dest_dir)
    return dest_dir


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