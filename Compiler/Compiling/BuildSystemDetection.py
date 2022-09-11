import os

from Compiling.GradleCompilingAndExtracting import compileReleaseGradle, findCompiledClassesGradle
from Compiling.MavenCompilingAndExtracting import compileReleaseMaven, findCompiledClassesMaven


def detectBuildSystem(pathOfProject):
    root, directories, files = next(os.walk(pathOfProject))
    if "mvnw" in files:
        return (compileReleaseMaven,findCompiledClassesMaven)
    elif "gradlew" in files:
        return (compileReleaseGradle,findCompiledClassesGradle)
    else:
        raise Exception("nessun build system individuato")