package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.Bridge;
import patterns.Strategy;
import structures.Project;

public class StrategyParser extends SinglePatternParser<Strategy>{
  private Project project;

  private ClassBean context = null;

  private ClassBean strategy = null;

  private InstanceVariableBean strategyInstance = null;

  private MethodBean contextInterface = null;


  public StrategyParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    context = null;

    strategy = null;

    strategyInstance = null;

    contextInterface = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Strategy createResult() {
    if(context == null || contextInterface == null || strategyInstance == null || strategy == null)
    {
      return  null;
    }
    return new Strategy(context,strategy,strategyInstance,contextInterface);
  }

  @Override
  public void processStartElement(StartElement startElement) {

    if (startElement.getName().getLocalPart().equals("role"))
    {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Context" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          context = project.findClass(classPath);
        }
        case "Strategy" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          strategy = project.findClass(classPath);
        }
        case "ContextInterface()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          contextInterface = project.findMethod(methodPath);

        }
        case "strategy" -> {
          String instanceVariablePath = startElement.getAttributeByName(new QName("element")).getValue();
          strategyInstance = project.findInstanceVariable(instanceVariablePath);
        }
      }
    }

  }
}
