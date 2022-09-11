import os
import subprocess


def compileReleaseMaven(pathOfRepo,logPath):

    file = open(logPath,"w")


    args = ['./mvnw',"clean" ,"compiler:compile"]
    # "--no-build-cache" "--no-daemon" "clean"
    process = subprocess.Popen(args, shell=False, cwd=pathOfRepo, stdout=file,
                               stderr=file)
    process.wait()
    file.close()
    if process.returncode == 1:
        raise Exception("impossibile compilare {}".format(pathOfRepo))


def findCompiledClassesMaven(pathOfInput):
    paths = []
    for root, dirs, files in os.walk(pathOfInput):
        if "target" in dirs and any([".xml" in s for s in files]):
            classesPath = os.path.join(root, "target")
            name = os.path.basename(root)
            if os.path.isdir(classesPath):
                paths.append((classesPath,name))
    return paths