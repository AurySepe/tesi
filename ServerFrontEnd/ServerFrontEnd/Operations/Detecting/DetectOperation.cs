using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Detecting;

public class DetectOperation : RepositoryOperation
{
    public DetectData Data { get; set; }

    public DetectOperation(string repository, string state, string type, DetectData data) : base(repository, state, type)
    {
        Data = data;
    }
    
    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<DetectData>(value);
    }

    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }
    
    public const String TYPE = "DETECT";
}



public class DetectData
{
    public String DetectPath { get; set; }

    public DetectData(string detectPath)
    {
        this.DetectPath = detectPath;
    }
}