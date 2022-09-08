using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Metrics;

public class MetricsOperation : RepositoryOperation
{
    public MetricsData Data { get; set; }

    public MetricsOperation(string repository, string state, string type, MetricsData data) : base(repository, state, type)
    {
        Data = data;
    }


    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<MetricsData>(value);
    }

    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }
    
    public const String TYPE = "METRICS";
}

public class MetricsData
{
    public String MetricsPath { get; set; }

    public MetricsData(string metricsPath)
    {
        MetricsPath = metricsPath;
    }
}