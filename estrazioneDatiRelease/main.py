import os

from configurations.Configuration import Configuration
from configurations.JEditConfig import JeditConfig
from configurations.JHotDrawConfig import JHotDrawConfig
from functions import writeResult

METRICSPATH = "C:/Users/aurel/Desktop/TestSource/ReactiveX-RxJava/METRICS"
OUTPUTPATH = "C:/Users/aurel/Desktop/TestSource/ReactiveX-RxJava"


releasesList = [(os.path.join(next(os.walk(METRICSPATH))[0],dirs,"class metrics.csv"),os.path.join(next(os.walk(METRICSPATH))[0],dirs,"class smells.csv"),dirs) for dirs  in next(os.walk(METRICSPATH))[1]]
print(releasesList)
config = Configuration(OUTPUTPATH,releasesList)


writeResult(config)

