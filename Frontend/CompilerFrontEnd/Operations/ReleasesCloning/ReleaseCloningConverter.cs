using System.Text.Json;
using CompilerFrontEnd.Cloning;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.ReleasesCloning;

public class ReleaseCloningConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<ReleasesCloningOperation>(operation);
    }
}