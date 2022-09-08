package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Adapter implements DesignPattern{

  private ClassBean adapter;

  private ClassBean adaptee;

  private List<MethodBean> targets;

  private InstanceVariableBean adapteeInAdapter;

  public Adapter(ClassBean adapter, ClassBean adaptee,
                 List<MethodBean> targets,
                 InstanceVariableBean adapteeInAdapter) {
    this.adapter = adapter;
    this.adaptee = adaptee;
    this.targets = targets;
    this.adapteeInAdapter = adapteeInAdapter;
  }

  public ClassBean getAdapter() {
    return adapter;
  }

  public void setAdapter(ClassBean adapter) {
    this.adapter = adapter;
  }

  public ClassBean getAdaptee() {
    return adaptee;
  }

  public void setAdaptee(ClassBean adaptee) {
    this.adaptee = adaptee;
  }

  public List<MethodBean> getTargets() {
    return targets;
  }

  public void setTargets(List<MethodBean> targets) {
    this.targets = targets;
  }

  public InstanceVariableBean getAdapteeInAdapter() {
    return adapteeInAdapter;
  }

  public void setAdapteeInAdapter(InstanceVariableBean adapteeInAdapter) {
    this.adapteeInAdapter = adapteeInAdapter;
  }


  @Override
  public String toString() {
    return "Adapter{" +
        "adapter=" + adapter +
        ", adaptee=" + adaptee +
        ", targets=" + targets +
        ", adapteeInAdapter=" + adapteeInAdapter +
        '}';
  }

  public static final String TYPE = "(Object)Adapter";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean adapter = new DesignPatternClassBean(getAdapter(),TYPE,"adapter");
    DesignPatternClassBean adaptee = new DesignPatternClassBean(getAdaptee(),TYPE,"adaptee");
    classBeans.add(adaptee);
    classBeans.add(adapter);
    return classBeans;
  }
}
