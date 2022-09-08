package patterns;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import java.util.ArrayList;
import java.util.List;
import patterns.information.DesignPatternClassBean;

public class Singleton implements DesignPattern{

  private ClassBean singleton;

  public Singleton(ClassBean singleton) {
    this.singleton = singleton;
  }

  public ClassBean getSingleton() {
    return singleton;
  }

  public void setSingleton(ClassBean singleton) {
    this.singleton = singleton;
  }

  @Override
  public String toString() {
    return "Singleton{" +
        "singleton=" + singleton +
        '}';
  }

  public static final String TYPE = "Singleton";

  @Override
  public List<DesignPatternClassBean> getClassesAndRoles() {
    List<DesignPatternClassBean> classBeans = new ArrayList<>();
    DesignPatternClassBean class1 = new DesignPatternClassBean(getSingleton(),TYPE,"singleton");
    classBeans.add(class1);
    return classBeans;
  }
}
