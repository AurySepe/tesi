package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Decorator implements DesignPattern{

  private ClassBean decorator;

  private ClassBean decorated;

  private InstanceVariableBean decoratedInDecorator;

  private List<MethodBean> decoratedMethods;

  public Decorator(ClassBean decorator, ClassBean decorated,
                   InstanceVariableBean decoratedInDecorator,
                   List<MethodBean> decoratedMethods) {
    this.decorator = decorator;
    this.decorated = decorated;
    this.decoratedInDecorator = decoratedInDecorator;
    this.decoratedMethods = decoratedMethods;
  }

  public ClassBean getDecorator() {
    return decorator;
  }

  public void setDecorator(ClassBean decorator) {
    this.decorator = decorator;
  }

  public ClassBean getDecorated() {
    return decorated;
  }

  public void setDecorated(ClassBean decorated) {
    this.decorated = decorated;
  }

  public InstanceVariableBean getDecoratedInDecorator() {
    return decoratedInDecorator;
  }

  public void setDecoratedInDecorator(
      InstanceVariableBean decoratedInDecorator) {
    this.decoratedInDecorator = decoratedInDecorator;
  }

  public List<MethodBean> getDecoratedMethods() {
    return decoratedMethods;
  }

  public void setDecoratedMethods(
      List<MethodBean> decoratedMethods) {
    this.decoratedMethods = decoratedMethods;
  }

  @Override
  public String toString() {
    return "Decorator{" +
        "decorator=" + decorator +
        ", decorated=" + decorated +
        ", decoratedInDecorator=" + decoratedInDecorator +
        ", decoratedMethods=" + decoratedMethods +
        '}';
  }

  public static final String TYPE = "Decorator";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(decorator,TYPE,"decorator");
    DesignPatternClassBean class2 = new DesignPatternClassBean(decorated,TYPE,"decorated");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }

}
