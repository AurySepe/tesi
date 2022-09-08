import os
import shutil
import subprocess




def copyDirectory(dirFrom, dirTo, newName=None):
    if newName is None:
        dest_dir = os.path.join(dirTo, os.path.basename(dirFrom))
    else:
        dest_dir = os.path.join(dirTo, os.path.basename(newName))
    shutil.copytree(dirFrom, dest_dir)
    return dest_dir


def compileRelease(pathOfRepo,logPath):

    file = open(logPath,"w")


    args = ['./gradlew',"clean" ,"compileJava","--no-daemon"]
    # "--no-build-cache" "--no-daemon" "clean"
    process = subprocess.Popen(args, shell=False, cwd=pathOfRepo, stdout=file,
                               stderr=file)
    process.wait()
    file.close()
    if process.returncode == 1:
        raise Exception("impossibile compilare {}".format(pathOfRepo))

