package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.Decorator;
import structures.Project;

public class DecoratorParser extends SinglePatternParser<Decorator> {

  private Project project;

  private ClassBean decorator;

  private ClassBean decorated;

  private List<MethodBean> decoratedMethods = new ArrayList<>();

  private InstanceVariableBean decoratedInDecorator;

  public DecoratorParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {

    decorator = null;
    decorated = null;
    decoratedMethods = new ArrayList<>();
    decoratedInDecorator = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Decorator createResult() {
    if (decorator == null || decorated == null || decoratedMethods.isEmpty() ||
        decoratedInDecorator == null) {
      return null;
    }
    return new Decorator(decorator, decorated, decoratedInDecorator, decoratedMethods);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role")) {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Component" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          decorated = project.findClass(classPath);
        }
        case "Decorator" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          decorator = project.findClass(classPath);
        }
        case "Operation()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          MethodBean method = project.findMethod(methodPath);
          if (method != null) {
            decoratedMethods.add(method);
          }
        }
        case "component" -> {
          String instanceVariablePath =
              startElement.getAttributeByName(new QName("element")).getValue();
          decoratedInDecorator = project.findInstanceVariable(instanceVariablePath);
        }
      }
    }
  }
}
