package structures;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NormalProject implements Project {

  private List<PackageBean> packages;

  public NormalProject(List<PackageBean> packages) {
    this.packages = packages;
  }

  public NormalProject() {
    this.packages = new ArrayList<>();
  }

  @Override
  public Iterable<PackageBean> getPackages() {
    return packages;
  }

  @Override
  public Iterable<ClassBean> getClasses() {
    List<ClassBean> classes = new ArrayList<>();
    for (PackageBean packageBean : packages) {
      classes.addAll(packageBean.getClasses());
    }
    return classes;
  }

  @Override
  public void addClass(ClassBean bean) {
    String packageName = bean.getBelongingPackage();
    for (PackageBean packageBean : packages) {
      if (packageBean.getName().equals(packageName)) {
        packageBean.addClass(bean);
      }
    }
  }

  @Override
  public void addPackage(PackageBean packageBean) {
    packages.add(packageBean);
  }

  @Override
  public ClassBean findClass(String classPath) {
    int index = classPath.lastIndexOf(".");
    String packageName = classPath.substring(0, index);
    String className = classPath.substring(index + 1);
    for (PackageBean packageBean : packages) {
      if (packageBean.getName().equals(packageName)) {
        for (ClassBean classBean : packageBean.getClasses()) {
          if (classBean.getName().equals(className)) {
            return classBean;
          }
        }
      }
    }
    return null;
  }

  @Override
  public MethodBean findMethod(String methodPath) {
    int index = methodPath.lastIndexOf("::");

    String methodNameWithParenthesis = methodPath.substring(index + 2);
    int indexParenthesis = methodNameWithParenthesis.indexOf("(");
    String methodName = methodNameWithParenthesis.substring(0,indexParenthesis);
    String classPath = methodPath.substring(0,index);
    ClassBean classBean = findClass(classPath);
    if (classBean == null)
    {
      return null;
    }
    for (MethodBean methodBean : classBean.getMethods())
    {
      if(methodBean.getName().equals(methodName))
      {
        return methodBean;
      }
    }
    return null;
  }

  @Override
  public InstanceVariableBean findInstanceVariable(String instanceVariablePath) {
    int index = instanceVariablePath.lastIndexOf("::");
    String classPath = instanceVariablePath.substring(0,index);

    int endOfvariableName = instanceVariablePath.lastIndexOf(":");
    String variableName = instanceVariablePath.substring(index + 2,endOfvariableName);

    ClassBean classBean = findClass(classPath);
    if(classBean == null)
    {
      return null;
    }
    for(InstanceVariableBean instanceVariableBean : classBean.getInstanceVariables())
    {
      if(instanceVariableBean.getName().equals(variableName))
      {
        return instanceVariableBean;
      }
    }
    return null;
  }
}
