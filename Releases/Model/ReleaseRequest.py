from dataclasses import dataclass


@dataclass
class ReleaseRequest:
    RepositoryOrigin : str


    @classmethod
    def fromJson(cls,json):
        return ReleaseRequest(**json)