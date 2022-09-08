namespace CompilerFrontEnd.Operations.Detecting;

public class DetectRequest
{
    public String BuildPath { get; set; }
    
    public String Repository { get; set; }
    
    public String RepositoryName { get; set; }

    public DetectRequest(string buildPath, string repository, string repositoryName)
    {
        BuildPath = buildPath;
        Repository = repository;
        RepositoryName = repositoryName;
    }
}