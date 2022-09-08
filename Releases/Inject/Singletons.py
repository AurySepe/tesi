from Messages.FeedbackProducer import FeedbackProducer
from Messages.RequestHandler import RequestHandler
from Messages.RequestListner import RequestListner

Producer = FeedbackProducer("rabbitmq","feedback")
Handler = RequestHandler(Producer)
Listner = RequestListner("rabbitmq","release",Handler)