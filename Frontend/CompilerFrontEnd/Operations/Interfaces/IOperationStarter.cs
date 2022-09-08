using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations;

public interface IOperationStarter
{
    public void Start(Repository repository);
}