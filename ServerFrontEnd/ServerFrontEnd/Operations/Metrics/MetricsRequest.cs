namespace ServerFrontEnd.Operations.Metrics;

public class MetricsRequest
{
    public String ProjectPath { get; set; }

    public String HistoryPath { get; set; }

    public String ProjectName { get; set; }

    public String RepositoryOrigin { get; set; }

    public String OrderedReleasePath { get; set; }
    
    public String ReleasesPath { get; set; }

    public MetricsRequest(string projectPath, string historyPath, string projectName, string repositoryOrigin, string orderedReleasePath, string releasesPath)
    {
        ProjectPath = projectPath;
        HistoryPath = historyPath;
        ProjectName = projectName;
        RepositoryOrigin = repositoryOrigin;
        OrderedReleasePath = orderedReleasePath;
        ReleasesPath = releasesPath;
    }
}