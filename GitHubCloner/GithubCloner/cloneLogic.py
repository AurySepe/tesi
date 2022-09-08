import git



def clone(origin, pathToClone):
    git.Git(pathToClone).clone(origin)


