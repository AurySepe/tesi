using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations.Compiling;
using CompilerFrontEnd.Operations.Detecting;
using CompilerFrontEnd.Services.RepositoryExtensions;

namespace CompilerFrontEnd.Operations.History;

public class HistoryStarter : IOperationStarter
{
    
    private MessageHandler handler;

    public HistoryStarter(MessageHandler handler)
    {
        this.handler = handler;
    }

    public void Start(Repository repository)
    {
        String clonePath = Path.Join(repository.Cloning().Data.ResultName,repository.Name);
        HistoryRequest request = new HistoryRequest(repository.OriginUrl,repository.Detecting().Data.DetectPath,repository.Cloning().Data.ResultName,clonePath);
        String requestJson = JsonSerializer.Serialize(request);
        handler.requestOperation(requestJson, "history");
    }
}