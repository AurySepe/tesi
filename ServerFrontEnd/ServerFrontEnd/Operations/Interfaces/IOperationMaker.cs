using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.Interfaces;

public interface IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository);

    public RepositoryOperation MakeCreatedOperation(Repository repository);
}