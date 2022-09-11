from ReleaseLogic import getReleases

ORIGIN = "https://github.com/ReactiveX/RxJava.git"

OUTPUTPATH = "releases.txt"

releases = getReleases(origin=ORIGIN)



with open(OUTPUTPATH,"w") as outputFile:
    outputFile.writelines([release + "\n" for release in releases])
