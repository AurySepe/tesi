using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.ReleasesCloning;

public class ReleasesCloningMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new ReleasesCloningOperation(repository.OriginUrl, RepositoryOperation.READY,
            ReleasesCloningOperation.TYPE, new ReleasesCloningData(""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new ReleasesCloningOperation(repository.OriginUrl, RepositoryOperation.CREATED,
            ReleasesCloningOperation.TYPE, new ReleasesCloningData(""));
    }
}