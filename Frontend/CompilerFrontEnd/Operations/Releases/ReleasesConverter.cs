using System.Text.Json;
using CompilerFrontEnd.Cloning;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Releases;

public class ReleasesConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<ReleasesOperation>(operation);
    }
}