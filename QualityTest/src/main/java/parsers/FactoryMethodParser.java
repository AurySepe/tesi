package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import patterns.FactoryMethod;
import structures.Project;

public class FactoryMethodParser extends SinglePatternParser<FactoryMethod> {

  private Project project;

  private ClassBean classBean;
  private MethodBean methodBean;

  public FactoryMethodParser(Project project) {
    this.project = project;
  }


  @Override
  protected void reset() {
    classBean = null;
    methodBean = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected FactoryMethod createResult() {
    if (classBean == null || methodBean == null)
    {
      return null;
    }
    return new FactoryMethod(classBean, methodBean);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role"))
    {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Creator" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          classBean = project.findClass(classPath);
        }
        case "FactoryMethod()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          methodBean = project.findMethod(methodPath);
        }
      }
    }

  }
}
