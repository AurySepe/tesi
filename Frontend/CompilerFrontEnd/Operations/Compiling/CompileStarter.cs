using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Model.Requests;
using CompilerFrontEnd.Operations.ReleasesCloning;
using CompilerFrontEnd.Services.RepositoryExtensions;

namespace CompilerFrontEnd.Operations.Compiling;

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