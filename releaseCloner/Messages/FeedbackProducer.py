import json

import pika

from Model.RepositoryOperation import RepositoryOperation
from configuration import OPERATIONNAME


class FeedbackProducer:

    def __init__(self,server,queque):
        self.server = server
        self.queque = queque

    def _sendMessage(self,message):
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=self.server))
        channel = connection.channel()

        channel.exchange_declare(exchange=self.queque, exchange_type='fanout')
        channel.basic_publish(exchange=self.queque, routing_key=self.queque,
                              body=message)
        connection.close()


    def success(self,result,origin):
        releasesCloningResultJson = json.dumps(result.__dict__)
        operation = RepositoryOperation(origin, OPERATIONNAME, RepositoryOperation.SUCCEDED,
                                        releasesCloningResultJson)
        message = json.dumps(operation.__dict__)
        self._sendMessage(message)

    def failure(self,result,origin):
        cloningResultJson = json.dumps(result.__dict__)
        operation = RepositoryOperation(origin, OPERATIONNAME, RepositoryOperation.FAILED,
                                        cloningResultJson)
        message = json.dumps(operation.__dict__)
        self._sendMessage(message)