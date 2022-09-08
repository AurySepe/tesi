using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations.Compiling;
using CompilerFrontEnd.Services.RepositoryExtensions;

namespace CompilerFrontEnd.Operations.Detecting;

public class DetectStarter : IOperationStarter
{

    private MessageHandler handler;

    public DetectStarter(MessageHandler handler)
    {
        this.handler = handler;
    }

    public void Start(Repository repository)
    {
        DetectRequest request = new DetectRequest(repository.Compile().Data.BuildPath,repository.OriginUrl,repository.Cloning().Data.ResultName);
        String requestJson = JsonSerializer.Serialize(request);
        handler.requestOperation(requestJson, "detect");
    }
}