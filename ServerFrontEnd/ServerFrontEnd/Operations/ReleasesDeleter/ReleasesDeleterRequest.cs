namespace ServerFrontEnd.Operations.ReleasesDeleter;

public class ReleasesDeleterRequest
{
    public String RepositoryOrigin { get; set; }
    public String ReleasesPath { get; set; }

    public ReleasesDeleterRequest(string repositoryOrigin, string releasesPath)
    {
        RepositoryOrigin = repositoryOrigin;
        ReleasesPath = releasesPath;
    }
}