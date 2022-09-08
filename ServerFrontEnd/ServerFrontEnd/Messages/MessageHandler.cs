using System.Text;
using System.Text.Json;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Messages;

public class MessageHandler
{

    private OperationConfigurationStorage ConfigurationStorage;
    
    public MessageHandler(OperationConfigurationStorage ConfigurationStorage)
    {
        this.ConfigurationStorage = ConfigurationStorage;

    }

    public void startConnection(OperationManager.OperationManager operationManager)
    {
        var factory = new ConnectionFactory { HostName = "rabbitmq" ,RequestedHeartbeat = TimeSpan.FromSeconds(10)}; 


        var connection = factory.CreateConnection(); 
        using var channel = connection.CreateModel();
        channel.ExchangeDeclare("feedback",ExchangeType.Fanout);
        var queueName = channel.QueueDeclare().QueueName;
        channel.QueueBind(queue: queueName,
            exchange: "feedback",
            routingKey: "");

        var consumer = new EventingBasicConsumer(channel);
        consumer.Received += (model, eventArgs) =>
        {
            
            var body = eventArgs.Body.ToArray();
            var message = Encoding.UTF8.GetString(body);
            System.Console.WriteLine($"Recived feedback: {message}");
            RepositoryOperation ope = JsonSerializer.Deserialize<RepositoryOperation>(message);
            RepositoryOperation op = ConfigurationStorage.Configurations[ope.Type].Converter.Convert(message);
            operationManager.NotifyOperation(op);
        };
        channel.BasicConsume(queue: queueName, autoAck: true, consumer: consumer);
        Console.WriteLine("ok");
        Thread.Sleep(Timeout.Infinite);
    }

    public void requestOperation(String operation,String destination)
    {
        System.Console.WriteLine($"Received a Request {operation} and {destination}");
        var factory = new ConnectionFactory { HostName = "rabbitmq" };
        var connection = factory.CreateConnection();
        using var channel = connection.CreateModel();
        channel.QueueDeclare(destination,exclusive:false,autoDelete:false);
        var body = Encoding.UTF8.GetBytes(operation);
        channel.BasicPublish(exchange: "", routingKey: destination, body: body);

    }
}