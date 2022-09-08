package Model;

public class DetectRequest {

  public static final String TYPE = "DETECT";

  private String BuildPath;


  private String Repository;

  private String RepositoryName;

  public DetectRequest(String buildPath, String repository,
                       String respositoryName) {
    this.BuildPath = buildPath;
    this.Repository = repository;
    this.RepositoryName = respositoryName;
  }

  public String getBuildPath() {
    return BuildPath;
  }

  public void setBuildPath(String buildPath) {
    this.BuildPath = buildPath;
  }

  public String getRepository() {
    return Repository;
  }

  public void setRepository(String repository) {
    this.Repository = repository;
  }

  public String getRepositoryName() {
    return RepositoryName;
  }

  public void setRepositoryName(String repositoryName) {
    this.RepositoryName = repositoryName;
  }

}
