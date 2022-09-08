namespace ServerFrontEnd.Operations.History;

public class HistoryRequest
{
    public String RepositoryOrigin    { get; set; }
    
    public String[] Releases { get; set; }
    public String RepositoryName      { get; set; }
    public String RepositoryClonePath { get; set; }

    public HistoryRequest(string repositoryOrigin, string[] releases, string repositoryName, string repositoryClonePath)
    {
        RepositoryOrigin = repositoryOrigin;
        Releases = releases;
        RepositoryName = repositoryName;
        RepositoryClonePath = repositoryClonePath;
    }
}