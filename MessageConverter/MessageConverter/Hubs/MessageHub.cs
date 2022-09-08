using System.Text;
using Microsoft.AspNetCore.SignalR;
using RabbitMQ.Client;

namespace MessageConverter.Hubs;

public class MessageHub : Hub
{
    public async Task SendMessageToClient(String operation)
    {
        System.Console.WriteLine(operation);
        await Clients.All.SendAsync("ReceiveClient", operation);
    }
    
    public async Task SendMessageToQueue(String operation,String destination)
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