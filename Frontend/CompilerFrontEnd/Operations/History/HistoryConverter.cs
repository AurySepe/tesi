using System.Text.Json;
using CompilerFrontEnd.Cloning;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.History;

public class HistoryConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<HistoryOperation>(operation);
    }
}