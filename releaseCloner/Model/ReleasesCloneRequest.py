from dataclasses import dataclass


@dataclass
class ReleasesCloneRequest:
    RepositoryOrigin : str
    RepositoryName : str
    ReleasesFilePath : str
    RepositoryActualName : str


    @classmethod
    def fromJson(cls,json):
        return ReleasesCloneRequest(**json)