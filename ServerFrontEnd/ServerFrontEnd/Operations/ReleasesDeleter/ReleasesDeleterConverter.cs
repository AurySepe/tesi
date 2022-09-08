using System.Text.Json;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.ReleasesCloning;

namespace ServerFrontEnd.Operations.ReleasesDeleter;

public class ReleasesDeleterConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<ReleasesDeleterOperation>(operation);
    }
}