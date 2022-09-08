from dataclasses import dataclass


@dataclass
class CloningRequest:
    Origin: str


    @classmethod
    def fromJson(cls,json):
        return CloningRequest(**json)