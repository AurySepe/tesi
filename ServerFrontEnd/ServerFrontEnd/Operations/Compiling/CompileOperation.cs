using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Compiling;

public class CompileOperation : RepositoryOperation
{
    public CompileData Data { get; set; }

    public CompileOperation(string repository, string state, string type, CompileData data) : base(repository, state, type)
    {
        Data = data;
    }

    protected override void JsonToResult(string value)
    {
        base.JsonToResult(value);
        Data = JsonSerializer.Deserialize<CompileData>(value);
    }

    protected override string ResultToJson()
    {
        return JsonSerializer.Serialize(Data);

    }
    
    public const String TYPE = "COMPILING";
}

public class CompileData
{

    public String CompiledSuccessFileRelativePath { get; set; }
    
    public String BuildPath { get; set; }

    public CompileData(string compiledSuccessFileRelativePath, string buildPath)
    {
        CompiledSuccessFileRelativePath = compiledSuccessFileRelativePath;
        BuildPath = buildPath;
    }
}