package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class State implements DesignPattern{

  private ClassBean state;

  private ClassBean context;

  private InstanceVariableBean stateInContext;

  private List<MethodBean> methodsAffectedByState;

  public State(ClassBean state, ClassBean context,
               InstanceVariableBean stateInContext,
               List<MethodBean> methodsAffectedByState) {
    this.state = state;
    this.context = context;
    this.stateInContext = stateInContext;
    this.methodsAffectedByState = methodsAffectedByState;
  }


  public ClassBean getState() {
    return state;
  }

  public void setState(ClassBean state) {
    this.state = state;
  }

  public ClassBean getContext() {
    return context;
  }

  public void setContext(ClassBean context) {
    this.context = context;
  }

  public InstanceVariableBean getStateInContext() {
    return stateInContext;
  }

  public void setStateInContext(InstanceVariableBean stateInContext) {
    this.stateInContext = stateInContext;
  }

  public List<MethodBean> getMethodsAffectedByState() {
    return methodsAffectedByState;
  }

  public void setMethodsAffectedByState(
      List<MethodBean> methodsAffectedByState) {
    this.methodsAffectedByState = methodsAffectedByState;
  }

  @Override
  public String toString() {
    return "State{" +
        "state=" + state +
        ", context=" + context +
        ", stateInContext=" + stateInContext +
        ", methodsAffectedByState=" + methodsAffectedByState +
        '}';
  }

  public static final String TYPE = "State";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(state,TYPE,"state");
    DesignPatternClassBean class2 = new DesignPatternClassBean(context,TYPE,"context");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }
}
