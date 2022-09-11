from Compiling.BuildSystemDetection import detectBuildSystem
from Messages.RequestHandler import extractBuild

#repo to compile
PROJECTPATH = "..."
#file for compilation log
LOGPATHS = ".../log.txt"
#output directory of .class file compiled
OUTPUTDIR = ""
try:
    compile,execute = detectBuildSystem(pathOfProject=PROJECTPATH)

    compile(PROJECTPATH,LOGPATHS)
    extractBuild(PROJECTPATH,OUTPUTDIR,execute)
except Exception as e:
    print(e)