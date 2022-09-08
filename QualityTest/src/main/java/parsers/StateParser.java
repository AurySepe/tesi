package parsers;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import patterns.State;
import structures.Project;

public class StateParser extends SinglePatternParser<State>{

  private Project project;

  private ClassBean state;

  private ClassBean context;

  private InstanceVariableBean stateInContext;

  private List<MethodBean> methodsAffectedByState = new ArrayList<>();

  public StateParser(Project project) {
    this.project = project;
  }

  @Override
  protected void reset() {
    state = null;
    context = null;
    stateInContext = null;
    methodsAffectedByState = new ArrayList<>();

  }

  @Override
  protected boolean processEndElement(EndElement endElement) {
    return endElement.getName().getLocalPart().equals("instance");
  }

  @Override
  protected State createResult() {
    if(state == null || stateInContext == null || methodsAffectedByState.isEmpty() || context == null)
    {
      return null;
    }

    return new State(state,context,stateInContext,methodsAffectedByState);
  }

  @Override
  public void processStartElement(StartElement startElement) {
    if (startElement.getName().getLocalPart().equals("role")) {
      switch (startElement.getAttributeByName(new QName("name")).getValue()) {
        case "State" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          state = project.findClass(classPath);
        }
        case "Context" -> {
          String classPath = startElement.getAttributeByName(new QName("element")).getValue();
          context = project.findClass(classPath);
        }
        case "Request()" -> {
          String methodPath = startElement.getAttributeByName(new QName("element")).getValue();
          MethodBean method = project.findMethod(methodPath);
          if (method != null) {
            methodsAffectedByState.add(method);
          }
        }
        case "state" -> {
          String instanceVariablePath =
              startElement.getAttributeByName(new QName("element")).getValue();
          stateInContext = project.findInstanceVariable(instanceVariablePath);
        }
      }
    }
  }
}
