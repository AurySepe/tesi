using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Releases;

public class ReleasesMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new ReleasesOperation(repository.OriginUrl, RepositoryOperation.READY, ReleasesOperation.TYPE,
            new ReleasesData(new string[] {} ));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new ReleasesOperation(repository.OriginUrl, RepositoryOperation.CREATED, ReleasesOperation.TYPE,
            new ReleasesData(new string[] {} ));
    }
}