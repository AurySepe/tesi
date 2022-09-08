using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Compiling;

public class CompileMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new CompileOperation(repository.OriginUrl, RepositoryOperation.READY, CompileOperation.TYPE,
            new CompileData("",""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new CompileOperation(repository.OriginUrl, RepositoryOperation.CREATED, CompileOperation.TYPE,
            new CompileData("",""));
    }
}