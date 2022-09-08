using System.Text.Json;
using CompilerFrontEnd.Cloning;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.Metrics;

public class MetricsConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<MetricsOperation>(operation);
    }
}