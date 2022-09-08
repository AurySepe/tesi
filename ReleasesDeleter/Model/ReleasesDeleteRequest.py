from dataclasses import dataclass


@dataclass
class ReleasesDeleteRequest:
    RepositoryOrigin : str
    ReleasesPath : str


    @classmethod
    def fromJson(cls,json):
        return ReleasesDeleteRequest(**json)