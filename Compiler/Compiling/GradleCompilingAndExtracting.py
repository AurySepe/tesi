import os
import subprocess


def compileReleaseGradle(pathOfRepo,logPath):

    file = open(logPath,"w")


    args = ['./gradlew',"clean" ,"compileJava","--no-daemon"]
    # "--no-build-cache" "--no-daemon" "clean"
    process = subprocess.Popen(args, shell=False, cwd=pathOfRepo, stdout=file,
                               stderr=file)
    process.wait()
    file.close()
    if process.returncode == 1:
        raise Exception("impossibile compilare {}".format(pathOfRepo))


def findCompiledClassesGradle(pathOfInput):
    paths = []
    for root, dirs, files in os.walk(pathOfInput):
        if "build" in dirs and any([".gradle" in s for s in files]):
            classesPath = os.path.join(root, "build")
            name = os.path.basename(root)
            if os.path.isdir(classesPath):
                paths.append((classesPath,name))
    return paths