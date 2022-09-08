namespace CompilerFrontEnd.Operations.ReleasesCloning;

public class ReleasesCloningRequest
{
    public String RepositoryOrigin { get; set; }
    public String RepositoryName { get; set; }
    public String[] Releases { get; set; }

    public ReleasesCloningRequest(string repositoryOrigin, string repositoryName, string[] releases)
    {
        RepositoryOrigin = repositoryOrigin;
        RepositoryName = repositoryName;
        Releases = releases;
    }
}