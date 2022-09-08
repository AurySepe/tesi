using CompilerFrontEnd.Maker;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.Compiling;

public class CompileMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new CompileOperation(repository.OriginUrl, RepositoryOperation.READY, CompileOperation.TYPE,
            new CompileData(new String[] { }, new String[] { },""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new CompileOperation(repository.OriginUrl, RepositoryOperation.CREATED, CompileOperation.TYPE,
            new CompileData(new String[] { }, new String[] { },""));
    }
}