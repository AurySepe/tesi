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
import patterns.Singleton;
import structures.Project;

public class SingletonParser extends SinglePatternParser<Singleton>{

  private Project project;

  private ClassBean classBean;

  public SingletonParser(Project project) {
    this.project = project;
  }


  @Override
  protected void reset() {
    classBean = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Singleton createResult() {
    if (classBean == null)
    {
      return null;
    }
    return new Singleton(classBean);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role"))
    {
      if ("Singleton".equals(startElement.getAttributeByName(new QName("name")).getValue())) {
        String classPath = startElement.getAttributeByName(new QName("element")).getValue();
        classBean = project.findClass(classPath);
      }
    }
  }
}
