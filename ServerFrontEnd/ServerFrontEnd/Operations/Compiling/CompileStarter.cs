using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.ReleasesCloning;

namespace ServerFrontEnd.Operations.Compiling;

public class CompileStarter : IOperationStarter
{
    
    private MessageHandler messageHandler;

    public CompileStarter(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }

    public void Start(Repository repository)
    {
        CompileRequest request = new CompileRequest(repository.OriginUrl,repository.ReleasesCloning().Data.ResultPath,repository.Cloning().Data.ResultName);
        String requestJson = JsonSerializer.Serialize(request);
        messageHandler.requestOperation(requestJson, "compile");
    }
}