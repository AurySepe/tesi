using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Metrics;

public class MetricsMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new MetricsOperation(repository.OriginUrl, RepositoryOperation.READY, MetricsOperation.TYPE,
            new MetricsData(""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new MetricsOperation(repository.OriginUrl, RepositoryOperation.CREATED, MetricsOperation.TYPE,
            new MetricsData(""));
    }
}