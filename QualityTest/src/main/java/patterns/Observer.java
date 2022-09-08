package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Observer implements DesignPattern{

  private ClassBean observer;

  private ClassBean subject;

  private MethodBean notify;

  public Observer(ClassBean observer, ClassBean subject,
                  MethodBean notify) {
    this.observer = observer;
    this.subject = subject;
    this.notify = notify;
  }

  public ClassBean getObserver() {
    return observer;
  }

  public void setObserver(ClassBean observer) {
    this.observer = observer;
  }

  public ClassBean getSubject() {
    return subject;
  }

  public void setSubject(ClassBean subject) {
    this.subject = subject;
  }

  public MethodBean getNotify() {
    return notify;
  }

  public void setNotify(MethodBean notify) {
    this.notify = notify;
  }

  public static final String TYPE = "Observer";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(getObserver(),TYPE,"observer");
    DesignPatternClassBean class2 = new DesignPatternClassBean(getSubject(),TYPE,"subject");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }

}
