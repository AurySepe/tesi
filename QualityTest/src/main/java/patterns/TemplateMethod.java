package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class TemplateMethod implements DesignPattern{

  private ClassBean abstractClass;

  private List<MethodBean> templateMethods;

  public TemplateMethod(ClassBean abstractClass,
                        List<MethodBean> templateMethods) {
    this.abstractClass = abstractClass;
    this.templateMethods = templateMethods;
  }

  public ClassBean getAbstractClass() {
    return abstractClass;
  }

  public void setAbstractClass(ClassBean abstractClass) {
    this.abstractClass = abstractClass;
  }

  public List<MethodBean> getTemplateMethods() {
    return templateMethods;
  }

  public void setTemplateMethods(
      List<MethodBean> templateMethods) {
    this.templateMethods = templateMethods;
  }

  @Override
  public String toString() {
    return "TemplateMethod{" +
        "abstractClass=" + abstractClass +
        ", templateMethods=" + templateMethods +
        '}';
  }

  public static final String TYPE = "Template Method";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(abstractClass,TYPE,"abstractClass");
    classBeans.add(class1);
    return classBeans;
  }

}
