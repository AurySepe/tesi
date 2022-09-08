using System.Text.Json;
using CompilerFrontEnd.Cloning;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.Detecting;

public class DetectConverter : IOperationConverter
{
    public RepositoryOperation Convert(string operation)
    {
        return JsonSerializer.Deserialize<DetectOperation>(operation);
    }
}