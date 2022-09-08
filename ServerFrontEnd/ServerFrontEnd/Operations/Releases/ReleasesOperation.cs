using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Releases;

public class ReleasesOperation : RepositoryOperation
{
    public ReleasesData Data { get; set; }

    
    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<ReleasesData>(value);
    }
    
    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }

    public ReleasesOperation(string repository, string state, string type, ReleasesData data) : base(repository, state, type)
    {
        Data = data;
    }

    public const String TYPE = "RELEASES";
}

public class ReleasesData
{
    public String[] Commits { get; set; }

    public ReleasesData(string[] commits)
    {
        Commits = commits;
    }
    
    
}