package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.Bridge;
import patterns.Observer;
import structures.Project;

public class ObserverParser extends SinglePatternParser<Observer>{
  private Project project;

  private ClassBean observer = null;

  private ClassBean  subject = null;

  private MethodBean notify = null;


  public ObserverParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    observer = null;

    subject = null;

    notify = null;
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Observer createResult() {
    if(observer == null || subject == null || notify == null)
    {
      return  null;
    }
    return new Observer(observer,subject,notify);
  }

  @Override
  public void processStartElement(StartElement startElement) {

    if (startElement.getName().getLocalPart().equals("role"))
    {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Observer" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          observer = project.findClass(classPath);
        }
        case "Subject" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          subject = project.findClass(classPath);
        }
        case "Notify()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          notify = project.findMethod(methodPath);

        }
      }
    }

  }
}
