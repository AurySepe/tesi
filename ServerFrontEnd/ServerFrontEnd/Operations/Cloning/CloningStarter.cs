using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Cloning;

public class CloningStarter : IOperationStarter
{
    private MessageHandler messageHandler;

    public CloningStarter(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }
    
    public void Start(Repository repository)
    {
        CloningRequest request = new CloningRequest(repository.OriginUrl);
        String requestJson = JsonSerializer.Serialize(request);
        messageHandler.requestOperation(requestJson, "clone");
    }

    
}