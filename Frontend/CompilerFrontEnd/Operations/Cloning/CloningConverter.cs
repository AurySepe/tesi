using System.Text.Json;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Cloning;

public class CloningConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<CloningOperation>(operation);
    }
}