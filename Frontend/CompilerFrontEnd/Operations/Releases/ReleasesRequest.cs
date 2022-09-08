namespace CompilerFrontEnd.Releases;

public class ReleasesRequest
{
    public String RepositoryOrigin { get; set; }

    public ReleasesRequest(string repositoryOrigin)
    {
        RepositoryOrigin = repositoryOrigin;
    }
}