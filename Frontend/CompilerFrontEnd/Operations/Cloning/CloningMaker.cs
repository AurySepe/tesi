using CompilerFrontEnd.Maker;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Cloning;

public class CloningMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return  new CloningOperation(repository.OriginUrl,RepositoryOperation.READY,CloningOperation.TYPE,new CloningData(""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return  new CloningOperation(repository.OriginUrl,RepositoryOperation.CREATED,CloningOperation.TYPE,new CloningData(""));

    }
}