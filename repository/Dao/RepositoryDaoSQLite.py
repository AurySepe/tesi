
from Dao.RepositoryDaoI import RepositoryDaoI
from Model.Repository import Repository


class RepositoryDaoSQLite(RepositoryDaoI):



    def __init__(self,session,engine):
        self.session = session
        self.engine = engine

    def getAllRepos(self):
        local_session = self.session(bind=self.engine)
        repos = local_session.query(Repository).all()
        return repos

    def getRepo(self,id):
        local_session = self.session(bind=self.engine)
        repo = local_session.query(Repository).filter(Repository.OriginUrl == id).first()
        return repo

    def createNewRepo(self,repository):
        local_session = self.session(bind=self.engine)
        local_session.add(repository)
        local_session.commit()

    def updateRepo(self,repository):
        local_session = self.session(bind=self.engine)
        local_session.merge(repository)
        local_session.commit()

    def deleteRepo(self,id):
        local_session = self.session(bind=self.engine)
        repo = local_session.query(Repository).filter(Repository.OriginUrl == id).first()
        local_session.delete(repo)
        local_session.commit()
