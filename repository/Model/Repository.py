from dataclasses import dataclass

from sqlalchemy import Column, String
from sqlalchemy.orm import relationship

from Configuration.db import Base


@dataclass
class Repository(Base):
    OriginUrl: str
    Author : str
    Name : str
    Status : str


    __tablename__ = 'Repository'

    OriginUrl = Column(String(255), primary_key=True)
    Author = Column(String(255))
    Name = Column(String(255))
    Status = Column(String(255))
    operations = relationship("RepositoryOperation",backref = "operations",cascade='all,delete')

    @classmethod
    def fromJSON(cls,json):
        return Repository(**json)
