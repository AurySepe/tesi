using CompilerFrontEnd.Maker;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.History;

public class HistoryMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new HistoryOperation(repository.OriginUrl, RepositoryOperation.READY, HistoryOperation.TYPE,
            new HistoryData("",""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new HistoryOperation(repository.OriginUrl, RepositoryOperation.CREATED, HistoryOperation.TYPE,
            new HistoryData("",""));
    }
}