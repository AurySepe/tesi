package metrics.calculators;

import history.HistoryClassIdentifier;
import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.metrics.CKMetrics;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import metrics.writer.beans.ClassMetricsBean;
import patterns.information.DesignPatternClassBean;
import patterns.information.DesignPatternInformationFinder;
import structures.Project;

public class ClassMetricCalculator {

  private HistoryClassIdentifier classIdentifier;

  private DesignPatternInformationFinder designPatternInformationFinder;

  private Project project;

  public ClassMetricCalculator(HistoryClassIdentifier classIdentifier,
                               DesignPatternInformationFinder designPatternInformationFinder,
                               Project project) {
    this.classIdentifier = classIdentifier;
    this.designPatternInformationFinder = designPatternInformationFinder;
    this.project = project;
  }

  public ClassMetricsBean calculateMetrics(ClassBean classBean) {
    Vector<ClassBean> classBeans = new Vector<>();
    project.getClasses().forEach(classBeans::add);


    String fullName = classBean.getBelongingPackage() + "." + classBean.getName();

    DesignPatternClassBean designPatternClassBean = designPatternInformationFinder.findDesignPatternClassInformation(fullName);

    int id = classIdentifier.getHystoryId(fullName);
    String patternType = null;
    String patternRole = null;
    if(designPatternClassBean != null)
    {
      patternType = designPatternClassBean.getPatternType();
      patternRole = designPatternClassBean.getClassRole();
    }
    int loc = CKMetrics.getLOC(classBean);
    int eloc = CKMetrics.getELOC(classBean);
    int numberOfMethods = classBean.getMethods().size();
    int numberOfAttributes = classBean.getInstanceVariables().size();
    int mcCabeMetric = CKMetrics.getMcCabeMetric(classBean);
    int halsteadVocabulary = CKMetrics.getHalsteadVocabulary(classBean);
    int halsteadLenght = CKMetrics.getHalsteadLenght(classBean);
    double halsteadVolume = CKMetrics.getHalsteadVolume(classBean);
    int numbOfChildren = CKMetrics.getNOC(classBean, classBeans);
    int RFC = CKMetrics.getRFC(classBean);
    int numberOfImports = CKMetrics.getCBO(classBean);
    int MPC = CKMetrics.getMPC(classBean);
    int DAC = CKMetrics.getDAC2(classBean);
    int LCOM = CKMetrics.getLCOM2(classBean);
    int COH = -1;
    try
    {
      COH = CKMetrics.getCoh(classBean);
    }
    catch (Exception ignored){}
    int TCC = -1;
    try
    {
      TCC = CKMetrics.getTCC(classBean);
    }
    catch (Exception ignored){}

    int LCC = -1;//CKMetrics.getLCC(classBean); getParameters is null
    try
    {
      LCC = CKMetrics.getLCC(classBean);
    }
    catch (Exception ignored){}
    int NOA = CKMetrics.getNOA(classBean, classBeans);
    int NOO = CKMetrics.getNOO(classBean, classBeans);
    return new ClassMetricsBean(id,fullName,patternType,patternRole, loc, eloc, numberOfMethods, numberOfAttributes,
        mcCabeMetric, halsteadVocabulary, halsteadLenght, halsteadVolume, numbOfChildren, RFC,
        numberOfImports, MPC, DAC, LCOM, COH, TCC, LCC, NOA, NOO);
  }

}
