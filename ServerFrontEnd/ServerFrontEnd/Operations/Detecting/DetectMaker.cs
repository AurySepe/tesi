using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Detecting;

public class DetectMaker : IOperationMaker
{
    public RepositoryOperation MakeReadyOperation(Repository repository)
    {
        return new DetectOperation(repository.OriginUrl, RepositoryOperation.READY, DetectOperation.TYPE,
            new DetectData(""));
    }

    public RepositoryOperation MakeCreatedOperation(Repository repository)
    {
        return new DetectOperation(repository.OriginUrl, RepositoryOperation.CREATED, DetectOperation.TYPE,
            new DetectData(""));
    }
}