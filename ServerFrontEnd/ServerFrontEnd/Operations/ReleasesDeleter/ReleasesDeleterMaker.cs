using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.ReleasesDeleter;

public class ReleasesDeleterMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new ReleasesDeleterOperation(repository.OriginUrl, RepositoryOperation.READY,
            ReleasesDeleterOperation.TYPE, new ReleasesDeleterData(false));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new ReleasesDeleterOperation(repository.OriginUrl, RepositoryOperation.CREATED,
            ReleasesDeleterOperation.TYPE, new ReleasesDeleterData(false));
    }
}