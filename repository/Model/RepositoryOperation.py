from dataclasses import dataclass

from sqlalchemy import Column, String, ForeignKey

from Configuration.db import Base


@dataclass
class RepositoryOperation(Base):

    __tablename__ = 'RepositoryOperation'

    Repository : str
    Type: str
    State : str
    Result : str

    Repository = Column(String(255),ForeignKey("Repository.OriginUrl"),primary_key=True)
    Type = Column(String(255),primary_key=True)
    State = Column(String(255))
    Result = Column(String())


    @classmethod
    def fromJSON(cls,json):
        return RepositoryOperation(**json)
