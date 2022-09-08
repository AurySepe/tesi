import os
import sys

from Configuration.Injection.Singletons import RequestHandler
from Messages.FeedbackRequestHandler import FeedbackRequestHandler
from Messages.RequestListner import RequestListner

Listner = RequestListner("rabbitmq", "feedback", RequestHandler)

if __name__ == '__main__':
    print("listening")
    Listner.listen()
