using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.ReleasesDeleter;

public static class ReleasesDeleterExtension
{
    public static ReleasesDeleterOperation ReleasesDeleter(this Repository repository)
    {
        return (ReleasesDeleterOperation)repository.operations[ReleasesDeleterOperation.TYPE];
    }
    
    public static void ReleasesDeleter(this Repository repository,ReleasesDeleterOperation operation)
    {
        repository.operations[ReleasesDeleterOperation.TYPE] = operation;
    }
}