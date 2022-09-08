using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Maker;

public interface IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository);

    public RepositoryOperation MakeCreatedOperation(Repository repository);
}