from Configuration.db import Base, engine
from Model.Repository import Repository
from Model.RepositoryOperation import RepositoryOperation

models = [Repository,RepositoryOperation]

Base.metadata.create_all(engine)

engine = engine

