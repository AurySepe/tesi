using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.Metrics;

public static class MetricsExtension
{
    public static MetricsOperation Metrics(this Repository repository)
    {
        return (MetricsOperation)repository.operations[MetricsOperation.TYPE];
    }
    
    public static void Metrics(this Repository repository,MetricsOperation value)
    {
        repository.operations[MetricsOperation.TYPE] = value;
    }
}