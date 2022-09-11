import os
from dataclasses import dataclass


@dataclass
class Configuration:


    OutputPath :str
    FileList : list

    @property
    def SmellOutputPath(self):
        return os.path.join(self.OutputPath,"smellsCount.csv")

    @property
    def DesignOutputPath(self):
        return os.path.join(self.OutputPath, "designPatternsClassCount.csv")

    @property
    def DesignAndSmellOutputPath(self):
        return os.path.join(self.OutputPath, "smellsAndDesignCount.csv")

