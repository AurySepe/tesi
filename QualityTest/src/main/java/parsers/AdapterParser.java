package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import patterns.Adapter;
import patterns.FactoryMethod;
import structures.Project;

public class AdapterParser extends SinglePatternParser<Adapter> {

  private Project project;

  private ClassBean adapter;

  private ClassBean adaptee;

  private List<MethodBean> targets = new ArrayList<>();

  private InstanceVariableBean adapteeInAdapter;

  public AdapterParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    adapter = null;
    adaptee = null;
    targets = new ArrayList<>();
    adapteeInAdapter = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Adapter createResult() {
    if(adapter == null || adaptee == null || targets.isEmpty() || adapteeInAdapter == null)
    {
      return null;
    }
    return new Adapter(adapter,adaptee,targets,adapteeInAdapter);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role"))
    {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Adapter" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          adapter = project.findClass(classPath);
        }
        case "Adaptee" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          adaptee = project.findClass(classPath);
        }
        case "Request()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          MethodBean target = project.findMethod(methodPath);
          if(target != null)
          {
            targets.add(target);
          }
        }
        case "adaptee" -> {
          String instanceVariablePath = startElement.getAttributeByName(new QName("element")).getValue();
          adapteeInAdapter = project.findInstanceVariable(instanceVariablePath);
        }
      }
    }
  }
}

