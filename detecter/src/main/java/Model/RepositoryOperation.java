package Model;

public class RepositoryOperation {

  public static final String FAILED = "FAILED";
  public static final String SUCCEDED = "SUCCEDED";
  private String Repository;

  private String Type;

  private String State;

  private String Result;

  public RepositoryOperation(String repository, String type, String state, String result) {
    this.Repository = repository;
    this.Type = type;
    this.State = state;
    this.Result = result;
  }

  public String getRepository() {
    return Repository;
  }

  public void setRepository(String repository) {
    this.Repository = repository;
  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    this.Type = type;
  }

  public String getState() {
    return State;
  }

  public void setState(String state) {
    this.State = state;
  }

  public String getResult() {
    return Result;
  }

  public void setResult(String result) {
    this.Result = result;
  }
}
