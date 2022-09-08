namespace ServerFrontEnd.Operations.ReleasesCloning;

public class ReleasesCloningRequest
{
    public String RepositoryOrigin { get; set; }
    public String RepositoryName { get; set; }
    public String ReleasesFilePath { get; set; }
    
    public String RepositoryActualName { get; set; }

    public ReleasesCloningRequest(string repositoryOrigin, string repositoryName, string releasesFilePath, string repositoryActualName)
    {
        RepositoryOrigin = repositoryOrigin;
        RepositoryName = repositoryName;
        ReleasesFilePath = releasesFilePath;
        RepositoryActualName = repositoryActualName;
    }
}