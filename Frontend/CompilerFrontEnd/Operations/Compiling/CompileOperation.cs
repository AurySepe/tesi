using System.Text.Json;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.Compiling;

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

    public String[] SuccessCompile { get; set; }
    public String[] FailedCompile { get; set; }
    
    public String BuildPath { get; set; }

    public CompileData(string[] successCompile, string[] failedCompile, string buildPath)
    {
        SuccessCompile = successCompile;
        FailedCompile = failedCompile;
        BuildPath = buildPath;
    }
}