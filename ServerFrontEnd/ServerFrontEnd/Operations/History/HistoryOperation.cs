using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.History;

public class HistoryOperation : RepositoryOperation
{
    public HistoryData Data { get; set; }

    public HistoryOperation(string repository, string state, string type, HistoryData data) : base(repository, state, type)
    {
        Data = data;
    }


    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<HistoryData>(value);
    }

    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }
    
    public const String TYPE = "HISTORY";
}

public class HistoryData
{
    
    public String HistoryClassPath { get; set; }
    
    public String OrderedReleasePath { get; set; }

    public HistoryData(string historyClassPath, string orderedReleasePath)
    {
        HistoryClassPath = historyClassPath;
        OrderedReleasePath = orderedReleasePath;
    }
}