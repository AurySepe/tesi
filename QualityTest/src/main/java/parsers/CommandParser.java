package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.Bridge;
import patterns.Command;
import structures.Project;

public class CommandParser extends SinglePatternParser<Command>{
  private Project project;

  private ClassBean receiver = null;

  private ClassBean concreteCommand = null;

  private InstanceVariableBean receiverInstance = null;

  private MethodBean execute = null;


  public CommandParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    receiver = null;

    concreteCommand = null;

    receiverInstance = null;

    execute = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Command createResult() {
    if(receiver == null || concreteCommand == null || execute == null || receiverInstance == null)
    {
      return  null;
    }
    return new Command(receiver,concreteCommand,receiverInstance,execute);
  }

  @Override
  public void processStartElement(StartElement startElement) {

    if (startElement.getName().getLocalPart().equals("role"))
    {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Receiver" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          receiver = project.findClass(classPath);
        }
        case "ConcreteCommand" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          concreteCommand = project.findClass(classPath);
        }
        case "Execute()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          execute = project.findMethod(methodPath);

        }
        case "receiver" -> {
          String instanceVariablePath = startElement.getAttributeByName(new QName("element")).getValue();
          receiverInstance = project.findInstanceVariable(instanceVariablePath);
        }
      }
    }

  }
}
