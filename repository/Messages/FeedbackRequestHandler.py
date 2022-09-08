from Dao.RepositoryOperationDaoI import RepositoryOperationDaoI
from Model.RepositoryOperation import RepositoryOperation


class FeedbackRequestHandler:

    def __init__(self,repositoryOperationDao : RepositoryOperationDaoI):
        self.repositoryOperationDao = repositoryOperationDao


    def handleRequest(self,request):
        repositoryOperation = RepositoryOperation.fromJSON(request)
        self.repositoryOperationDao.updateRepositoryOperation(repositoryOperation)
