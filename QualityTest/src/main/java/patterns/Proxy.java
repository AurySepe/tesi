package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Proxy implements DesignPattern{

  private ClassBean proxy;

  private ClassBean realSubject;

  private List<MethodBean> proxyedMethods;

  public Proxy(ClassBean proxy, ClassBean realSubject,
               List<MethodBean> proxyedMethods) {
    this.proxy = proxy;
    this.realSubject = realSubject;
    this.proxyedMethods = proxyedMethods;
  }


  public ClassBean getProxy() {
    return proxy;
  }

  public void setProxy(ClassBean proxy) {
    this.proxy = proxy;
  }

  public ClassBean getRealSubject() {
    return realSubject;
  }

  public void setRealSubject(ClassBean realSubject) {
    this.realSubject = realSubject;
  }

  public List<MethodBean> getProxyedMethods() {
    return proxyedMethods;
  }

  public void setProxyedMethods(List<MethodBean> proxyedMethods) {
    this.proxyedMethods = proxyedMethods;
  }

  public static final String TYPE = "Proxy";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(proxy,TYPE,"proxy");
    DesignPatternClassBean class2 = new DesignPatternClassBean(realSubject,TYPE,"realsubject");
    classBeans.add(class1);
    classBeans.add(class2);
    return classBeans;
  }
}
