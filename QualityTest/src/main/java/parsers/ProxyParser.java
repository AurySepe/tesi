package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.Decorator;
import patterns.Proxy;
import structures.Project;

public class ProxyParser extends SinglePatternParser<Proxy>{

  private Project project;

  private ClassBean proxy;

  private ClassBean realSubject;

  private List<MethodBean> proxyedMethods = new ArrayList<>();

  public ProxyParser(Project project) {
    this.project = project;
  }



  @Override
  protected void reset() {
    proxy = null;
    realSubject = null;
    proxyedMethods = new ArrayList<>();
  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected Proxy createResult() {
    if (proxy == null || realSubject == null || proxyedMethods.isEmpty()) {
      return null;
    }
    return new Proxy(proxy,realSubject,proxyedMethods);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role")) {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "Proxy" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          proxy = project.findClass(classPath);
        }
        case "RealSubject" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          realSubject = project.findClass(classPath);
        }
        case "Request()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          MethodBean method = project.findMethod(methodPath);
          if (method != null) {
            proxyedMethods.add(method);
          }
        }
      }
    }

  }
}
