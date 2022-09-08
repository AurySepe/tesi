package patterns.information;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;

public class DesignPatternClassBean {

  private ClassBean aClass;

  private String patternType;

  private String classRole;

  public DesignPatternClassBean(ClassBean aClass, String patternType, String classRole) {
    this.aClass = aClass;
    this.patternType = patternType;
    this.classRole = classRole;
  }

  public ClassBean getaClass() {
    return aClass;
  }

  public void setaClass(ClassBean aClass) {
    this.aClass = aClass;
  }

  public String getPatternType() {
    return patternType;
  }

  public void setPatternType(String patternType) {
    this.patternType = patternType;
  }

  public String getClassRole() {
    return classRole;
  }

  public void setClassRole(String classRole) {
    this.classRole = classRole;
  }
}
