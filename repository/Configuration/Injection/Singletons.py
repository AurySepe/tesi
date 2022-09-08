from sqlalchemy.orm import sessionmaker

from Configuration.createDB import engine
from Dao.RepositoryDaoSQLite import RepositoryDaoSQLite
from Dao.RepositoryOperationDaoSQLite import RepositoryOperationDaoSQLite
from Messages.FeedbackRequestHandler import FeedbackRequestHandler
from Messages.RequestListner import RequestListner

DbSession = sessionmaker()
DbEngine = engine

RepositoryDao = RepositoryDaoSQLite(DbSession,DbEngine)
RepositoryOperationDao = RepositoryOperationDaoSQLite(DbSession,DbEngine)



RequestHandler = FeedbackRequestHandler(RepositoryOperationDao)
