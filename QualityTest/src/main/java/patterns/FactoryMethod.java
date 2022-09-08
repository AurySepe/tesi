package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class FactoryMethod implements DesignPattern{

  private ClassBean creator;

  private MethodBean factoryMethod;

  public FactoryMethod(ClassBean creator, MethodBean factoryMethod) {
    this.creator = creator;
    this.factoryMethod = factoryMethod;
  }

  public ClassBean getCreator() {
    return creator;
  }

  public void setCreator(ClassBean creator) {
    this.creator = creator;
  }

  public MethodBean getFactoryMethod() {
    return factoryMethod;
  }

  public void setFactoryMethod(MethodBean factoryMethod) {
    this.factoryMethod = factoryMethod;
  }

  @Override
  public String toString() {
    return "FactoryMethod{" +
        "creator=" + creator +
        ", factoryMethod=" + factoryMethod +
        '}';
  }

  public static final String TYPE = "Factory Method";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class2 = new DesignPatternClassBean(getCreator(),TYPE,"creator");
    classBeans.add(class2);
    return classBeans;
  }
}
