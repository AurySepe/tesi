using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Cloning;

public interface IOperationConverter
{
    public RepositoryOperation Convert(String operation);
}