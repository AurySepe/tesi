package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Strategy implements DesignPattern{

  private ClassBean context;

  private ClassBean strategy;

  private InstanceVariableBean strategyInstance;

  private MethodBean contextInterface;


  public Strategy(ClassBean context, ClassBean strategy,
                  InstanceVariableBean strategyInstance,
                  MethodBean contextInterface) {
    this.context = context;
    this.strategy = strategy;
    this.strategyInstance = strategyInstance;
    this.contextInterface = contextInterface;
  }


  public ClassBean getContext() {
    return context;
  }

  public void setContext(ClassBean context) {
    this.context = context;
  }

  public ClassBean getStrategy() {
    return strategy;
  }

  public void setStrategy(ClassBean strategy) {
    this.strategy = strategy;
  }

  public InstanceVariableBean getStrategyInstance() {
    return strategyInstance;
  }

  public void setStrategyInstance(InstanceVariableBean strategyInstance) {
    this.strategyInstance = strategyInstance;
  }

  public MethodBean getContextInterface() {
    return contextInterface;
  }

  public void setContextInterface(MethodBean contextInterface) {
    this.contextInterface = contextInterface;
  }

  public static final String TYPE = "Strategy";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(context,TYPE,"context");
    DesignPatternClassBean class2 = new DesignPatternClassBean(strategy,TYPE,"strategy");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }
}
