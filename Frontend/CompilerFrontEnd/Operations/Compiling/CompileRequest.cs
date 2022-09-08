namespace CompilerFrontEnd.Operations.Compiling;

public class CompileRequest
{
    public String RepositoryOrigin { get; set; }
    
    public String ReleasesPath { get; set; }
    
    public String RepositoryName { get; set; }

    public CompileRequest(string repositoryOrigin, string releasesPath, string repositoryName)
    {
        RepositoryOrigin = repositoryOrigin;
        ReleasesPath = releasesPath;
        RepositoryName = repositoryName;
    }
}