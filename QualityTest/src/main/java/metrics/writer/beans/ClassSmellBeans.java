package metrics.writer.beans;

public class ClassSmellBeans {

  private int id;

  private String name;

  private String patternType;

  private String patternRole;

  private boolean classDataShouldBePrivate;

  private boolean complexClass;

  private boolean functionalDecomposition;

  private boolean godClass;

  private boolean spaghettiCode;

  public ClassSmellBeans(int id, String name, String patternType, String patternRole,
                         boolean classDataShouldBePrivate, boolean complexClass,
                         boolean functionalDecomposition, boolean godClass, boolean spaghettiCode) {
    this.id = id;
    this.name = name;
    this.patternType = patternType;
    this.patternRole = patternRole;
    this.classDataShouldBePrivate = classDataShouldBePrivate;
    this.complexClass = complexClass;
    this.functionalDecomposition = functionalDecomposition;
    this.godClass = godClass;
    this.spaghettiCode = spaghettiCode;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isClassDataShouldBePrivate() {
    return classDataShouldBePrivate;
  }

  public void setClassDataShouldBePrivate(boolean classDataShouldBePrivate) {
    this.classDataShouldBePrivate = classDataShouldBePrivate;
  }

  public boolean isComplexClass() {
    return complexClass;
  }

  public void setComplexClass(boolean complexClass) {
    this.complexClass = complexClass;
  }

  public boolean isFunctionalDecomposition() {
    return functionalDecomposition;
  }

  public void setFunctionalDecomposition(boolean functionalDecomposition) {
    this.functionalDecomposition = functionalDecomposition;
  }

  public boolean isGodClass() {
    return godClass;
  }

  public void setGodClass(boolean godClass) {
    this.godClass = godClass;
  }

  public boolean isSpaghettiCode() {
    return spaghettiCode;
  }

  public void setSpaghettiCode(boolean spaghettiCode) {
    this.spaghettiCode = spaghettiCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPatternType() {
    return patternType;
  }

  public void setPatternType(String patternType) {
    this.patternType = patternType;
  }

  public String getPatternRole() {
    return patternRole;
  }

  public void setPatternRole(String patternRole) {
    this.patternRole = patternRole;
  }
}
