using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Model.Requests;
using CompilerFrontEnd.OperationManager;
using CompilerFrontEnd.Operations;

namespace CompilerFrontEnd.Cloning;

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