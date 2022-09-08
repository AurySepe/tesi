from dataclasses import dataclass


@dataclass
class ClassHistoryRequest:
    RepositoryOrigin : str
    Releases : list
    RepositoryName:str
    RepositoryClonePath : str


    @classmethod
    def fromJson(cls,json):
        return ClassHistoryRequest(**json)