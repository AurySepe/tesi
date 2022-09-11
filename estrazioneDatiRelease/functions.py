import pandas as pd

from configurations.Configuration import Configuration

COLUMNSPATTERNS = ["(Object)Adapter","Command","Strategy","Proxy","Bridge","Decorator","Observer","Singleton","State","Template Method","Factory Method"]
COLUMNSSMELLS = ["CLASSDATASHOULDBEPRIVATE","COMPLEXCLASS","FUNCTIONALDECOMPOSITION","GODCLASS","SPAGHETTICODE"]


def getPatternsClassNumber(filePath,name):


    data = pd.read_csv(filePath,sep="\t")


    designRows = data[data["PATTERNTYPE"].notna() ]
    notDesignRows = data[data["PATTERNTYPE"].isna()]

    designPatterns = designRows.groupby("PATTERNTYPE").size()
    designPatterns.name = name
    designPatterns["totalClassesPatterns"] = designPatterns.sum()
    designPatterns["totalClassesNONPatterns"] = len(notDesignRows.index)

    return designPatterns



def getSmellsClassNumber(filePath,name):


    resultSeries = pd.Series()

    data = pd.read_csv(filePath,sep="\t")
    for smellName in COLUMNSSMELLS:
        resultSeries[smellName] = len(data[data[smellName] == True])


    resultSeries["totalClasses"] = len(data.index)
    resultSeries.name = name
    return resultSeries

def writeResult(config : Configuration):
    patternsData = pd.DataFrame(columns=COLUMNSPATTERNS)
    smellsData = pd.DataFrame(columns=COLUMNSSMELLS)
    patternsAndSmellData = pd.DataFrame()
    for pathClasses, pathSmells, name in config.FileList:
        patternsData = patternsData.append(getPatternsClassNumber(pathClasses, name))
        smellsData = smellsData.append(getSmellsClassNumber(pathSmells, name))
        patternsAndSmellData = patternsAndSmellData.append(getSmellsForDesignPattern(pathSmells,name))
    patternsData.to_csv(config.DesignOutputPath, index_label="Version")
    smellsData.to_csv(config.SmellOutputPath, index_label="Version")
    patternsAndSmellData.to_csv(config.DesignAndSmellOutputPath,index_label="Version")


def getSmellsForDesignPattern(filePath,name):
    resultSeries = pd.Series()
    resultSeries.name = name


    data = pd.read_csv(filePath, sep="\t")
    dataSmells = data[COLUMNSSMELLS]
    designPatter = data[data["PATTERNTYPE"].notna()]
    smellClasses = data[dataSmells.any(axis="columns")]
    designPatterAndSmell = data[data["PATTERNTYPE"].notna() & dataSmells.any(axis="columns")]
    nither = data[data["PATTERNTYPE"].isna() & ~dataSmells.any(axis="columns")]

    resultSeries["totalClasses"] = len(data.index)
    resultSeries["totalDesignPatternClass"] = len(designPatter.index)
    resultSeries["totalSmellClasses"] = len(smellClasses.index)
    resultSeries["totalDesignPatternsAndSmellClasses"] = len(designPatterAndSmell.index)
    resultSeries["totalNotDesignPatternsAndNotSmellClasses"] = len(nither.index)
    return resultSeries





