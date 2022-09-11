from releaseHistoryLogic import getClassesHistory


#path to the repo to analize (can be a GitHub's url )
REPOPATH = ""

#directory of the output files
OUTPUTPATH = ""

#the releases to analize
RELEASES = []

#all releases that are found will be written here
FILEPATH = ""



getClassesHistory(repoPath=REPOPATH,outputDir=OUTPUTPATH,releases=RELEASES,releaseOutputFile=FILEPATH)