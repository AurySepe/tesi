// See https://aka.ms/new-console-template for more information

using System.Text;
using Microsoft.AspNetCore.SignalR.Client;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;

var hubConnection = new HubConnectionBuilder().WithUrl("http://hubs/messagehub")
    .Build();

await hubConnection.StartAsync();

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
    hubConnection.SendAsync("SendMessageToClient", message);
};
channel.BasicConsume(queue: queueName, autoAck: true, consumer: consumer);
Console.WriteLine("ok");
Thread.Sleep(Timeout.Infinite);
