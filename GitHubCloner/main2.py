#!/usr/bin/env python
import json

import pika

from Model.CloningRequest import CloningRequest

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.queue_declare(queue='clone')
cloningRequest = CloningRequest(Origin="https://github.com/AurySepe/AiRHockey.git",OutputPath="C:/Users/aurel/Desktop/TestSource")
channel.basic_publish(exchange='', routing_key='clone', body= json.dumps(cloningRequest.__dict__))
print(f" [x] Sent {json.dumps(cloningRequest.__dict__)}")
connection.close()