package Model;

public class MetricsRequest {

  public static final String TYPE = "METRICS";

  private String ProjectPath;

  private String HistoryPath;

  private String ProjectName;

  private String RepositoryOrigin;

  private String OrderedReleasePath;

  private String ReleasesPath;

  public MetricsRequest(String projectPath, String historyPath, String projectName,
                        String repositoryOrigin, String orderedReleasePath,
                        String releasesPath) {
    ProjectPath = projectPath;
    HistoryPath = historyPath;
    ProjectName = projectName;
    RepositoryOrigin = repositoryOrigin;
    OrderedReleasePath = orderedReleasePath;
    ReleasesPath = releasesPath;
  }

  public String getProjectPath() {
    return ProjectPath;
  }

  public void setProjectPath(String projectPath) {
    ProjectPath = projectPath;
  }

  public String getHistoryPath() {
    return HistoryPath;
  }

  public void setHistoryPath(String historyPath) {
    HistoryPath = historyPath;
  }

  public String getProjectName() {
    return ProjectName;
  }

  public void setProjectName(String projectName) {
    ProjectName = projectName;
  }

  public String getRepositoryOrigin() {
    return RepositoryOrigin;
  }

  public void setRepositoryOrigin(String repositoryOrigin) {
    RepositoryOrigin = repositoryOrigin;
  }

  public String getOrderedReleasePath() {
    return OrderedReleasePath;
  }

  public void setOrderedReleasePath(String orderedReleasePath) {
    OrderedReleasePath = orderedReleasePath;
  }

  public String getReleasesPath() {
    return ReleasesPath;
  }

  public void setReleasesPath(String releasesPath) {
    ReleasesPath = releasesPath;
  }
}
