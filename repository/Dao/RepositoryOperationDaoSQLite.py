from Dao.RepositoryOperationDaoI import RepositoryOperationDaoI
from Model.RepositoryOperation import RepositoryOperation


class RepositoryOperationDaoSQLite(RepositoryOperationDaoI):

    def __init__(self,session,engine):
        self.session = session
        self.engine = engine


    def getAllRepositoryOperation(self):
        local_session = self.session(bind=self.engine)
        repos = local_session.query(RepositoryOperation).all()
        return repos
    def getRepositoryOperation(self, repository, type):
        local_session = self.session(bind=self.engine)
        repo = local_session.query(RepositoryOperation).filter((RepositoryOperation.Repository == repository) & (RepositoryOperation.Type == type)).first()
        return repo
    def createNewRepositoryOperation(self, repositoryOperation):
        local_session = self.session(bind=self.engine)
        local_session.add(repositoryOperation)
        local_session.commit()
    def deleteRepositoryOperation(self, repository, type):
        local_session = self.session(bind=self.engine)
        repo = local_session.query(RepositoryOperation).filter((RepositoryOperation.Repository == repository) & (RepositoryOperation.Type == type)).first()
        local_session.delete(repo)
        local_session.commit()
    def updateRepositoryOperation(self, repositoryOperation):
        local_session = self.session(bind=self.engine)
        local_session.merge(repositoryOperation)
        local_session.commit()