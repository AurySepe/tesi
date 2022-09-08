from dataclasses import dataclass




@dataclass
class Repository:
    originUrl: str
    author : str
    name : str

    @classmethod
    def fromJSON(cls,json):
        return Repository(**json)
