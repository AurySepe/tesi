package metrics.writer.beans;

public class ProjectMetricBean {

  private int LOC;

  private int ELOC;

  private int numOfClasses;

  private double intraConnectivity;

  private double interConnectivity;

  public ProjectMetricBean(int LOC, int ELOC, int numOfClasses, double intraConnectivity,
                           double interConnectivity) {
    this.LOC = LOC;
    this.ELOC = ELOC;
    this.numOfClasses = numOfClasses;
    this.intraConnectivity = intraConnectivity;
    this.interConnectivity = interConnectivity;
  }

  public int getLOC() {
    return LOC;
  }

  public void setLOC(int LOC) {
    this.LOC = LOC;
  }

  public int getELOC() {
    return ELOC;
  }

  public void setELOC(int ELOC) {
    this.ELOC = ELOC;
  }

  public int getNumOfClasses() {
    return numOfClasses;
  }

  public void setNumOfClasses(int numOfClasses) {
    this.numOfClasses = numOfClasses;
  }

  public double getIntraConnectivity() {
    return intraConnectivity;
  }

  public void setIntraConnectivity(double intraConnectivity) {
    this.intraConnectivity = intraConnectivity;
  }

  public double getInterConnectivity() {
    return interConnectivity;
  }

  public void setInterConnectivity(double interConnectivity) {
    this.interConnectivity = interConnectivity;
  }

  @Override
  public String toString() {
    return "ProjectMetricBean{" +
        "LOC=" + LOC +
        ", ELOC=" + ELOC +
        ", numOfClasses=" + numOfClasses +
        ", intraConnectivity=" + intraConnectivity +
        ", interConnectivity=" + interConnectivity +
        '}';
  }
}
