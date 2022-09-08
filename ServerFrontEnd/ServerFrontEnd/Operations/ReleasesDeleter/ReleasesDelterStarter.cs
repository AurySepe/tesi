using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.ReleasesCloning;

namespace ServerFrontEnd.Operations.ReleasesDeleter;

public class ReleasesDelterStarter : IOperationStarter
{
    
    private MessageHandler messageHandler;

    public ReleasesDelterStarter(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }

    public void Start(Repository repository)
    {
        ReleasesDeleterRequest request =
            new ReleasesDeleterRequest(repository.OriginUrl, repository.ReleasesCloning().Data.ResultPath);
        String requestJson = JsonSerializer.Serialize(request);
        messageHandler.requestOperation(requestJson, "releasesdeleter");
    }
}