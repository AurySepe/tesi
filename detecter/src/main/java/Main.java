
import static Implementation.DetectHandler.VOLUME;

import Implementation.DetectHandler;
import Messages.FeedbackProducer;
import Messages.MessageListner;
import Messages.RequestHandler;
import api.Detecter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

public class Main {

  public static void main(String[] args) throws IOException, TimeoutException {

    FeedbackProducer feedbackProducer = new FeedbackProducer("feedback","rabbitmq");
    RequestHandler handler = new DetectHandler(feedbackProducer);
    MessageListner listner = new MessageListner("detect","rabbitmq",handler);

    listner.listen();




  }

}
