using System.Text.Json;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.ReleasesCloning;

public class ReleasesCloningOperation : RepositoryOperation
{
    public ReleasesCloningData Data { get; set; }


    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<ReleasesCloningData>(value);
    }

    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);
    }

    public ReleasesCloningOperation(string repository, string state, string type, ReleasesCloningData data) : base(repository, state, type)
    {
        Data = data;
    }

    public const String TYPE = "RELEASESCLONING";
}

public class ReleasesCloningData
{
    public String ResultPath { get; set; }

    public ReleasesCloningData(string resultPath)
    {
        ResultPath = resultPath;
    }
}