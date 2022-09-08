import Messages.FeedbackProducer;
import Messages.MessageListner;
import Messages.MetricsHandler;
import Messages.RequestHandler;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MainListner {
  public static void main(String[] args) throws IOException, TimeoutException {
    FeedbackProducer feedbackProducer = new FeedbackProducer("feedback","rabbitmq");
    RequestHandler handler = new MetricsHandler(feedbackProducer);
    MessageListner listner = new MessageListner("metrics","rabbitmq",handler);

    listner.listen();
  }
}
