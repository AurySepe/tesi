using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Metrics;

public class MetricsConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<MetricsOperation>(operation);
    }
}