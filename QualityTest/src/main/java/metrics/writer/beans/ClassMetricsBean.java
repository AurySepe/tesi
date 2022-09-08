package metrics.writer.beans;

public class ClassMetricsBean {

  private int id;

  private String name;

  private String patternType;

  private String patternRole;

  private int loc;

  private int eloc;

  private int numbOfMethods;

  private int numbOfAttributes;

  private int mcCabeMetric;

  private int halsteadVocabulary;

  private int halsteadLenght;

  private double halsteadVolume;

  private int numberOfChildren;

  private int RFC;

  private int numberOfImports;

  private int MPC;

  private int DAC;

  private int LCOM;

  private int COH;

  private int TCC;

  private int LCC;

  private int NOA;

  private int NOO;

  public ClassMetricsBean(int id, String name, String patternType, String patternRole, int loc,
                          int eloc, int numbOfMethods, int numbOfAttributes, int mcCabeMetric,
                          int halsteadVocabulary, int halsteadLenght, double halsteadVolume,
                          int numberOfChildren, int RFC, int numberOfImports, int MPC, int DAC,
                          int LCOM, int COH, int TCC, int LCC, int NOA, int NOO) {
    this.id = id;
    this.name = name;
    this.patternType = patternType;
    this.patternRole = patternRole;
    this.loc = loc;
    this.eloc = eloc;
    this.numbOfMethods = numbOfMethods;
    this.numbOfAttributes = numbOfAttributes;
    this.mcCabeMetric = mcCabeMetric;
    this.halsteadVocabulary = halsteadVocabulary;
    this.halsteadLenght = halsteadLenght;
    this.halsteadVolume = halsteadVolume;
    this.numberOfChildren = numberOfChildren;
    this.RFC = RFC;
    this.numberOfImports = numberOfImports;
    this.MPC = MPC;
    this.DAC = DAC;
    this.LCOM = LCOM;
    this.COH = COH;
    this.TCC = TCC;
    this.LCC = LCC;
    this.NOA = NOA;
    this.NOO = NOO;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLoc() {
    return loc;
  }

  public void setLoc(int loc) {
    this.loc = loc;
  }

  public int getEloc() {
    return eloc;
  }

  public void setEloc(int eloc) {
    this.eloc = eloc;
  }

  public int getNumbOfMethods() {
    return numbOfMethods;
  }

  public void setNumbOfMethods(int numbOfMethods) {
    this.numbOfMethods = numbOfMethods;
  }

  public int getNumbOfAttributes() {
    return numbOfAttributes;
  }

  public void setNumbOfAttributes(int numbOfAttributes) {
    this.numbOfAttributes = numbOfAttributes;
  }

  public int getMcCabeMetric() {
    return mcCabeMetric;
  }

  public void setMcCabeMetric(int mcCabeMetric) {
    this.mcCabeMetric = mcCabeMetric;
  }

  public int getHalsteadVocabulary() {
    return halsteadVocabulary;
  }

  public void setHalsteadVocabulary(int halsteadVocabulary) {
    this.halsteadVocabulary = halsteadVocabulary;
  }

  public int getHalsteadLenght() {
    return halsteadLenght;
  }

  public void setHalsteadLenght(int halsteadLenght) {
    this.halsteadLenght = halsteadLenght;
  }

  public double getHalsteadVolume() {
    return halsteadVolume;
  }

  public void setHalsteadVolume(double halsteadVolume) {
    this.halsteadVolume = halsteadVolume;
  }

  public int getNumberOfChildren() {
    return numberOfChildren;
  }

  public void setNumberOfChildren(int numberOfChildren) {
    this.numberOfChildren = numberOfChildren;
  }

  public int getRFC() {
    return RFC;
  }

  public void setRFC(int RFC) {
    this.RFC = RFC;
  }

  public int getNumberOfImports() {
    return numberOfImports;
  }

  public void setNumberOfImports(int numberOfImports) {
    this.numberOfImports = numberOfImports;
  }

  public int getMPC() {
    return MPC;
  }

  public void setMPC(int MPC) {
    this.MPC = MPC;
  }

  public int getDAC() {
    return DAC;
  }

  public void setDAC(int DAC) {
    this.DAC = DAC;
  }

  public int getLCOM() {
    return LCOM;
  }

  public void setLCOM(int LCOM) {
    this.LCOM = LCOM;
  }

  public int getCOH() {
    return COH;
  }

  public void setCOH(int COH) {
    this.COH = COH;
  }

  public int getTCC() {
    return TCC;
  }

  public void setTCC(int TCC) {
    this.TCC = TCC;
  }

  public int getLCC() {
    return LCC;
  }

  public void setLCC(int LCC) {
    this.LCC = LCC;
  }

  public int getNOA() {
    return NOA;
  }

  public void setNOA(int NOA) {
    this.NOA = NOA;
  }

  public int getNOO() {
    return NOO;
  }

  public void setNOO(int NOO) {
    this.NOO = NOO;
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
