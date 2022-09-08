class RepositoryService:

    def __init__(self,repositoryDao):
        self.repositoryDao = repositoryDao

    def checkRepository(self,id):
        return True

    def startCloning(self,repository):
        pass

    def cloningFailed(self,repository):
        pass

    def cloningSuccess(self,repository):
        pass