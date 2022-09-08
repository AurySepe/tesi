import json

import requests as requests

from Model.Repository import Repository
from RepositoryInterface.RepositoryDaoI import RepositoryDaoI


class RepositoryDaoServer(RepositoryDaoI):

    def __init__(self, serverUrl):
        self.serverUrl = serverUrl

    def getRepo(self, id):
        url = self.serverUrl + f"/getRepo/{id}"
        requestResult = requests.get(url)
        repository = Repository.fromJSON(requestResult.json())
        return repository

    def getAllRepos(self):
        url = self.serverUrl + "/getAllRepos"
        requestResult = requests.get(url)
        repositories = []
        for repository in requestResult.json():
            repositories.append(Repository.fromJSON(repository))
        return repositories

    def updateRepo(self,repository):
        url = self.serverUrl + "/updateRepo"
        result = requests.post(url,json=json.dumps(repository))

    def createNewRepo(self,repository):
        url = self.serverUrl + "/newRepo"
        result = requests.post(url,json=json.dumps(repository))

    def deleteRepo(self,id):
        url = self.serverUrl + f"/deleteRepo/{id}"
        requestResult = requests.post(url)
