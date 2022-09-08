from dataclasses import dataclass


@dataclass
class CompileResult:
    CompiledSuccessFileRelativePath : str
    BuildPath : str
