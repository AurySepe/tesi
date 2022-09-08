package Messages;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageListner {

  private String queque;

  private String host;

  private RequestHandler handler;

  public MessageListner(String queque, String host,RequestHandler handler) {
    this.queque = queque;
    this.host = host;
    this.handler = handler;
  }

  public void listen() throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(host);
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    channel.queueDeclare(queque, false, false, false, null);

    channel.basicQos(1);

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");

      System.out.println(" [x] Received '" + message + "'");
      handler.handle(message);
      System.out.println(" Request Handled");
      channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

    };
    channel.basicConsume(queque, false, deliverCallback, consumerTag -> {
    });
  }

}
