package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.TemplateMethod;
import structures.Project;

public class TemplateMethodParser extends SinglePatternParser<TemplateMethod>{

  private Project project;

  private ClassBean abstractClass;

  private List<MethodBean> templateMethods = new ArrayList<>();

  public TemplateMethodParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    abstractClass = null;
    templateMethods = new ArrayList<>();
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected TemplateMethod createResult() {
    if(abstractClass == null || templateMethods.isEmpty())
    {
      return null;
    }
    return new TemplateMethod(abstractClass,templateMethods);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role")) {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "AbstractClass" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          abstractClass = project.findClass(classPath);
        }

        case "TemplateMethod()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          MethodBean method = project.findMethod(methodPath);
          if (method != null) {
            templateMethods.add(method);
          }
        }

      }
    }
  }
}
