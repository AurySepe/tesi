using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.ReleasesDeleter;

public class ReleasesDeleterOperation : RepositoryOperation
{
    public ReleasesDeleterData Data { get; set; }

    
    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<ReleasesDeleterData>(value);
    }
    
    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }

    public ReleasesDeleterOperation(string repository, string state, string type, ReleasesDeleterData data) : base(repository, state, type)
    {
        Data = data;
    }

    public const String TYPE = "RELEASESDELETER";
}

public class ReleasesDeleterData
{
    public bool IsDeleteSuccess { get; set; }

    public ReleasesDeleterData(bool isDeleteSuccess)
    {
        IsDeleteSuccess = isDeleteSuccess;
    }
}