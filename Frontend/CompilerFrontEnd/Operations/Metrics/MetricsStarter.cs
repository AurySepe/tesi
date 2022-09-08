using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations.History;
using CompilerFrontEnd.Operations.ReleasesCloning;
using CompilerFrontEnd.Services.RepositoryExtensions;

namespace CompilerFrontEnd.Operations.Metrics;

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
            repository.Cloning().Data.ResultName, repository.OriginUrl, repository.History().Data.OrderedReleasePath,repository.ReleasesCloning().Data.ResultPath);
        String requestJson = JsonSerializer.Serialize(request);
        handler.requestOperation(requestJson, "metrics");
    }
}