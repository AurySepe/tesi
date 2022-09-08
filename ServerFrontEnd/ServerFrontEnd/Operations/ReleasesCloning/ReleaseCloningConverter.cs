using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.ReleasesCloning;

public class ReleaseCloningConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<ReleasesCloningOperation>(operation);
    }
}