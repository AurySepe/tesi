from dataclasses import dataclass




@dataclass
class RepositoryOperation:


    Repository : str
    Type: str
    State : str
    Result : str


    READY = "READY"
    PENDING = "PENDING"
    FAILED = "FAILED"
    SUCCEDED = "SUCCEDED"

