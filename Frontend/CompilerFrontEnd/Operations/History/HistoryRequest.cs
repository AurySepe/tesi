namespace CompilerFrontEnd.Operations.History;

public class HistoryRequest
{
    public String RepositoryOrigin    { get; set; }
    public String DetectPath          { get; set; }
    public String RepositoryName      { get; set; }
    public String RepositoryClonePath { get; set; }

    public HistoryRequest(string repositoryOrigin, string detectPath, string repositoryName, string repositoryClonePath)
    {
        RepositoryOrigin = repositoryOrigin;
        DetectPath = detectPath;
        RepositoryName = repositoryName;
        RepositoryClonePath = repositoryClonePath;
    }
    
    
}