using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations.Compiling;
using CompilerFrontEnd.Operations.Detecting;
using CompilerFrontEnd.Operations.History;
using CompilerFrontEnd.Operations.Metrics;
using CompilerFrontEnd.Operations.ReleasesCloning;
using CompilerFrontEnd.Releases;

namespace CompilerFrontEnd.Operations;

public class OperationConfigurationStorage
{
    public Dictionary<String, OperationConfiguration> Configurations { get; set; } =
        new Dictionary<string, OperationConfiguration>();

    public static readonly String[] TYPES = new[] { CloningOperation.TYPE,ReleasesOperation.TYPE,ReleasesCloningOperation.TYPE,CompileOperation.TYPE,DetectOperation.TYPE,HistoryOperation.TYPE,MetricsOperation.TYPE };
}