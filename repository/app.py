import multiprocessing

from flask import Flask, jsonify, request
from flask_cors import CORS

from Configuration.Injection.Singletons import RepositoryDao, RepositoryOperationDao
from Model.Repository import Repository

app = Flask(__name__)
CORS(app)



@app.route('/getAllRepos')
def getRepos():  # put application's code here
    repos = RepositoryDao.getAllRepos()
    return jsonify(repos)

@app.route('/getAllOperations')
def getAllOperations():
    operations = RepositoryOperationDao.getAllRepositoryOperation();
    return jsonify(operations)

@app.route('/newRepo',methods = ["POST"])
def createRepo():  # put application's code here
    body = request.get_json()
    del body["operations"]
    print(body)
    newRepo = Repository.fromJSON(body)
    try:
        RepositoryDao.createNewRepo(newRepo)
        return jsonify(created = True)
    except:
        return jsonify(created = False)


@app.route('/getRepo/<id>')
def getRepo(id):  # put application's code here
    repo = RepositoryDao.getRepo(id)
    return jsonify(repo)

@app.route('/updateRepo',methods = ["POST"])
def updateRepo():  # put application's code here
    body = request.get_json()
    updatedRepo = Repository.fromJSON(body)
    try:
        RepositoryDao.updateRepo(updatedRepo)
        return jsonify(updated=True)
    except:
        return jsonify(updated=False)

@app.route('/deleteRepo',methods = ["POST"])
def deleteRepo():  # put application's code here
    body = request.get_json()
    try:
        RepositoryDao.deleteRepo(body["id"])
        return jsonify(deleted=True)
    except:
        return jsonify(deleted=False)

@app.route('/deleteOperation',methods = ["POST"])
def deleteOperation():  # put application's code here
    body = request.get_json()
    try:
        RepositoryOperationDao.deleteRepositoryOperation(body["repository"],body["type"])
        return jsonify(deleted=True)
    except Exception as e:
        print(e)
        return jsonify(deleted=False)

if __name__ == '__main__':
    app.run()
