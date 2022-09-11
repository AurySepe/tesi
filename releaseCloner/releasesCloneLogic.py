import os.path
import shutil
import tempfile

from pydriller import Git

from Messages.RequestHandler import copyDirectory, onerror


def cloneReleases(pathToRepo, releases, outputDir):
    try:
        tempdir = tempfile.mkdtemp()
        copyDirectory(pathToRepo, tempdir)
        repoName = os.path.basename(pathToRepo)
        temRep = os.path.join(tempdir, repoName)
        rep = Git(temRep)
        for release in releases:
            rep.checkout(release)
            copyDirectory(temRep, outputDir, release)
    finally:
        shutil.rmtree(tempdir, onerror=onerror)
