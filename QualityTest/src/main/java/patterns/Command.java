package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Command implements DesignPattern{

  private ClassBean receiver;

  private ClassBean concreteCommand;

  private InstanceVariableBean receiverInstance;

  private MethodBean execute;

  public Command(ClassBean reciver, ClassBean concreteCommand,
                 InstanceVariableBean receiver, MethodBean execute) {
    this.receiver = reciver;
    this.concreteCommand = concreteCommand;
    this.receiverInstance = receiver;
    this.execute = execute;
  }

  public ClassBean getReceiver() {
    return receiver;
  }

  public void setReceiver(ClassBean receiver) {
    this.receiver = receiver;
  }

  public ClassBean getConcreteCommand() {
    return concreteCommand;
  }

  public void setConcreteCommand(ClassBean concreteCommand) {
    this.concreteCommand = concreteCommand;
  }

  public InstanceVariableBean getReceiverInstance() {
    return receiverInstance;
  }

  public void setReceiverInstance(InstanceVariableBean receiverInstance) {
    this.receiverInstance = receiverInstance;
  }

  public MethodBean getExecute() {
    return execute;
  }

  public void setExecute(MethodBean execute) {
    this.execute = execute;
  }

  public static final String TYPE = "Command";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(receiver,TYPE,"receiver");
    DesignPatternClassBean class2 = new DesignPatternClassBean(concreteCommand,TYPE,"concreteCommand");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }

}
