import json

from Inject.Singletons import Handler
from Model.ReleasesCloneRequest import ReleasesCloneRequest

r = ReleasesCloneRequest("https://github.com/ReactiveX/RxJava.git","C:/Users/aurel/Desktop/TestSource/RxJava",["88453711ec1b0e03eb7ba02d42b51fe1330b3a73"],"C:/Users/aurel/Desktop/TestSource")
request = json.dumps(r.__dict__)
request = json.loads(request)
Handler.handleRequest(request)