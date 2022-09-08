using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.Compiling;
using ServerFrontEnd.Operations.Detecting;
using ServerFrontEnd.Operations.History;
using ServerFrontEnd.Operations.Metrics;
using ServerFrontEnd.Operations.Releases;
using ServerFrontEnd.Operations.ReleasesCloning;
using ServerFrontEnd.Operations.ReleasesDeleter;

namespace ServerFrontEnd.Operations.Interfaces;

public class OperationConfigurationStorage
{
    public Dictionary<String, OperationConfiguration> Configurations { get; set; } =
        new Dictionary<string, OperationConfiguration>();

    public static readonly String[] TYPES = new[] { CloningOperation.TYPE,ReleasesOperation.TYPE,ReleasesCloningOperation.TYPE,CompileOperation.TYPE,DetectOperation.TYPE,HistoryOperation.TYPE,MetricsOperation.TYPE,ReleasesDeleterOperation.TYPE };
}