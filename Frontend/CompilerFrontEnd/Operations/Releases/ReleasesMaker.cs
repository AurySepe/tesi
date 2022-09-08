using CompilerFrontEnd.Maker;
using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Releases;

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