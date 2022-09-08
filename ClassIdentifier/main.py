import os
import sys

from Messages.FeedbackProducer import FeedbackProducer
from Messages.RequestHandler import RequestHandler
from Messages.RequestListner import RequestListner

Feedback = FeedbackProducer("rabbitmq","feedback")
Handler = RequestHandler(Feedback)
Listner = RequestListner("rabbitmq","history",Handler)


if __name__ == '__main__':
    try:
        print("Listening")
        Listner.listen()
    except  KeyboardInterrupt as e :
        print(e)
        try:
            sys.exit(0)
        except SystemExit:
            os._exit(0)
