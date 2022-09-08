from dataclasses import dataclass


@dataclass
class CompileRequest:
    RepositoryOrigin : str
    ReleasesPath : str
    RepositoryName:str


    @classmethod
    def fromJson(cls,json):
        return CompileRequest(**json)