package structures;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.InstanceVariableBean;
import it.unisa.codeSmellAnalyzer.beans.MethodBean;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;

public interface Project {

  Iterable<PackageBean> getPackages();

  Iterable<ClassBean> getClasses();

  void addClass(ClassBean bean);

  void addPackage(PackageBean packageBean);

  ClassBean findClass(String classPath);

  MethodBean findMethod(String methodPath);

  InstanceVariableBean findInstanceVariable(String instanceVariablePath);
}
