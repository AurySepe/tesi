package metrics.writer;

import structures.Project;

public abstract class ProjectWriter {

  private Project project;

  public ProjectWriter(Project project) {
    this.project = project;
  }

  public abstract void writeProjectData(String outputPath);

  public abstract void writeClassData(String outputPath);

  public abstract void writeClassSmells(String outputPath);

  public abstract void writeMethodData(String outputPath);

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }
}
