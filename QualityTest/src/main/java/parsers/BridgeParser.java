package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.Bridge;
import structures.Project;

public class BridgeParser extends SinglePatternParser<Bridge>{

  private Project project;

  private ClassBean abstractor = null;

  private ClassBean implementor = null;

  private InstanceVariableBean implementorInstance = null;

  private MethodBean operation = null;


  public BridgeParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    abstractor = null;

    implementor = null;

    implementorInstance = null;

    operation = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Bridge createResult() {
    if(abstractor == null || implementor == null || implementorInstance == null || operation == null)
    {
      return  null;
    }
    return new Bridge(abstractor,implementor,implementorInstance,operation);
  }

  @Override
  public void processStartElement(StartElement startElement) {

    if (startElement.getName().getLocalPart().equals("role"))
    {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Abstraction" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          abstractor = project.findClass(classPath);
        }
        case "Implementor" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          implementor = project.findClass(classPath);
        }
        case "Operation()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          operation = project.findMethod(methodPath);

        }
        case "implementor" -> {
          String instanceVariablePath = startElement.getAttributeByName(new QName("element")).getValue();
          implementorInstance = project.findInstanceVariable(instanceVariablePath);
        }
      }
    }

  }
}
