package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Bridge implements DesignPattern{

  private ClassBean abstractor;

  private ClassBean implementor;

  private InstanceVariableBean implementorInstance;

  private MethodBean operation;

  public Bridge(ClassBean abstractor, ClassBean implementor,
                InstanceVariableBean implementorInstance,
                MethodBean operation) {
    this.abstractor = abstractor;
    this.implementor = implementor;
    this.implementorInstance = implementorInstance;
    this.operation = operation;
  }

  public ClassBean getAbstractor() {
    return abstractor;
  }

  public void setAbstractor(ClassBean abstractor) {
    this.abstractor = abstractor;
  }

  public ClassBean getImplementor() {
    return implementor;
  }

  public void setImplementor(ClassBean implementor) {
    this.implementor = implementor;
  }

  public InstanceVariableBean getImplementorInstance() {
    return implementorInstance;
  }

  public void setImplementorInstance(
      InstanceVariableBean implementorInstance) {
    this.implementorInstance = implementorInstance;
  }

  public MethodBean getOperation() {
    return operation;
  }

  public void setOperation(MethodBean operation) {
    this.operation = operation;
  }

  public static final String TYPE = "Bridge";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(getAbstractor(),TYPE,"abstraction");
    DesignPatternClassBean class2 = new DesignPatternClassBean(getImplementor(),TYPE,"implementor");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }

}
