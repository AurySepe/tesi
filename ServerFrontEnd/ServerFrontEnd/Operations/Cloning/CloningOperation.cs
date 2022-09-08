using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Cloning;

public class CloningOperation : RepositoryOperation
{
    public CloningData Data { get; set; }

    public CloningOperation(string repository, string state, string type, CloningData data) : base(repository, state, type)
    {
        Data = data;
    }

    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<CloningData>(value);
    }

    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }
    
    public const String TYPE = "CLONING";
}

public class CloningData
{
    public String ResultName { get; set; }

    public CloningData(string ResultName)
    {
        this.ResultName = ResultName;
    }

    public CloningData()
    {
        ResultName = "";
    }
}