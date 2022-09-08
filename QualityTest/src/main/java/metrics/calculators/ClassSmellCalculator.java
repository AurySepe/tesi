package metrics.calculators;

import history.HistoryClassIdentifier;
import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.smellDetector.ClassDataShouldBePrivateRule;
import it.unisa.codeSmellAnalyzer.smellDetector.ComplexClassRule;
import it.unisa.codeSmellAnalyzer.smellDetector.FunctionalDecompositionRule;
import it.unisa.codeSmellAnalyzer.smellDetector.GodClassRule;
import it.unisa.codeSmellAnalyzer.smellDetector.SpaghettiCodeRule;
import metrics.writer.beans.ClassSmellBeans;
import patterns.information.DesignPatternClassBean;
import patterns.information.DesignPatternInformationFinder;
import structures.Project;

public class ClassSmellCalculator {

  private HistoryClassIdentifier classIdentifier;

  private DesignPatternInformationFinder designPatternInformationFinder;

  public ClassSmellCalculator(HistoryClassIdentifier classIdentifier,
                              DesignPatternInformationFinder designPatternInformationFinder) {
    this.classIdentifier = classIdentifier;
    this.designPatternInformationFinder = designPatternInformationFinder;
  }

  public ClassSmellBeans calculateSmell(ClassBean classBean)
  {
    String name = classBean.getBelongingPackage() + "." + classBean.getName();

    DesignPatternClassBean designPatternClassBean = designPatternInformationFinder.findDesignPatternClassInformation(name);

    int id = classIdentifier.getHystoryId(name);

    String patternType = null;
    String patternRole = null;
    if(designPatternClassBean != null)
    {
      patternType = designPatternClassBean.getPatternType();
      patternRole = designPatternClassBean.getClassRole();
    }
    boolean classDataShouldBePrivate = new ClassDataShouldBePrivateRule().isClassDataShouldBePrivate(classBean);
    boolean complexClass = new ComplexClassRule().isComplexClass(classBean);
    boolean functionalDecomposiotion = new FunctionalDecompositionRule().isFunctionalDecomposition(classBean);
    boolean godClass = new GodClassRule().isGodClass(classBean);
    boolean spaghettiCode = new SpaghettiCodeRule().isSpaghettiCode(classBean);
    return new ClassSmellBeans(id,name,patternType,patternRole,classDataShouldBePrivate,complexClass,functionalDecomposiotion,godClass,spaghettiCode);
  }

}
