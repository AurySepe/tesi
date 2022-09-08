using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.ReleasesCloning;

public static class ReleasesCloningExtension
{
    public static ReleasesCloningOperation ReleasesCloning(this Repository repository)
    {
        return (ReleasesCloningOperation)repository.operations[ReleasesCloningOperation.TYPE];
    }
    
    public static void ReleasesCloning(this Repository repository,ReleasesCloningOperation operation)
    {
        repository.operations[ReleasesCloningOperation.TYPE] = operation;
    }
}