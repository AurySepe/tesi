import json

import pika


class RequestListner:

    def __init__(self,server,queque,requestHandler):
        self.connection = pika.BlockingConnection(pika.ConnectionParameters(host=server))
        self.channel = self.connection.channel()


        self.channel.exchange_declare(exchange=queque, exchange_type='fanout')
        result = self.channel.queue_declare(queue='', exclusive=True)
        queue_name = result.method.queue
        self.channel.queue_bind(exchange=queque, queue=queue_name)

        def handlerWrapper(ch, method, properties, body):
            print(f"Recived Request {body}")
            requestJson = json.loads(body)
            requestHandler.handleRequest(requestJson)
            print("request Handled")

        self.channel.basic_consume(queue=queue_name, on_message_callback=handlerWrapper, auto_ack=True)


    def listen(self):
        self.channel.start_consuming()
