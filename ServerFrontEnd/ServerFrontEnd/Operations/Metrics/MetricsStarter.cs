using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.Compiling;
using ServerFrontEnd.Operations.History;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.ReleasesCloning;

namespace ServerFrontEnd.Operations.Metrics;

public class MetricsStarter : IOperationStarter
{
    
    private MessageHandler handler;

    public MetricsStarter(MessageHandler handler)
    {
        this.handler = handler;
    }

    public void Start(Repository repository)
    {
        String clonePath = Path.Join(repository.Cloning().Data.ResultName,repository.Name);
        MetricsRequest request = new MetricsRequest(clonePath, repository.History().Data.HistoryClassPath,
            repository.Cloning().Data.ResultName, repository.OriginUrl, repository.Compile().Data.CompiledSuccessFileRelativePath,repository.ReleasesCloning().Data.ResultPath);
        String requestJson = JsonSerializer.Serialize(request);
        handler.requestOperation(requestJson, "metrics");
    }
}