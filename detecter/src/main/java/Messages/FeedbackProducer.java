package Messages;

import Model.DetectRequest;
import Model.DetectResult;
import Model.RepositoryOperation;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FeedbackProducer {

  private String exchange;

  private String host;

  public FeedbackProducer(String exchange, String host) {
    this.exchange = exchange;
    this.host = host;
  }

  private void sendMessage(String message) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(host);
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(exchange, "fanout");


      channel.basicPublish(exchange, "", null, message.getBytes("UTF-8"));
    }
  }

  public void sendSuccess(DetectResult result,String repository)
  {
    Gson gson = new Gson();
    String resultJson = gson.toJson(result,DetectResult.class);
    RepositoryOperation r = new RepositoryOperation(repository,DetectRequest.TYPE,RepositoryOperation.SUCCEDED,resultJson);
    String message = gson.toJson(r,RepositoryOperation.class);
    try {
      sendMessage(message);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }

  }

  public void sendFailure(DetectResult result,String repository)
  {
    Gson gson = new Gson();
    String resultJson = gson.toJson(result,DetectResult.class);
    RepositoryOperation r = new RepositoryOperation(repository, DetectRequest.TYPE,RepositoryOperation.FAILED,resultJson);
    String message = gson.toJson(r,RepositoryOperation.class);
    try {
      sendMessage(message);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
