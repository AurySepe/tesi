using System.Text.Json;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations;
using Microsoft.AspNetCore.SignalR.Client;

namespace CompilerFrontEnd.Messages;

public class MessageHandler
{
    private HubConnection hubConnection;

    private OperationConfigurationStorage ConfigurationStorage;
    
    public MessageHandler(OperationConfigurationStorage ConfigurationStorage)
    {
        this.ConfigurationStorage = ConfigurationStorage;

    }

    public async Task startConnection(OperationManager.OperationManager operationManager)
    {
        hubConnection = new HubConnectionBuilder().WithUrl("http://localhost:5123/messagehub")
            .Build();

        hubConnection.On<string>("ReceiveClient", (operation) =>
        {
            RepositoryOperation ope = JsonSerializer.Deserialize<RepositoryOperation>(operation);
            RepositoryOperation op = ConfigurationStorage.Configurations[ope.Type].Converter.Convert(operation);
            operationManager.NotifyOperation(op);
            
        });
        await hubConnection.StartAsync();
    }

    public async Task requestOperation(String operation,String destination)
    {
        await hubConnection.SendAsync("SendMessageToQueue",operation, destination);

    }
}