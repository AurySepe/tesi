package metrics.calculators;

import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;
import it.unisa.codeSmellAnalyzer.metrics.CKMetrics;
import java.util.ArrayList;
import java.util.List;
import metrics.writer.beans.ProjectMetricBean;
import structures.Project;

public class ProjectMetricCalculator {



  private int totalLOC(Project project)
  {
    int total = 0;
    for(ClassBean classBean : project.getClasses())
    {
      total += CKMetrics.getLOC(classBean);
    }
    return total;
  }


  private int totalELOC(Project project)
  {
    int total = 0;
    for(ClassBean classBean : project.getClasses())
    {
      total += CKMetrics.getELOC(classBean);
    }
    return total;
  }

  private int totalClasses(Project project)
  {
    int total = 0;
    for(ClassBean classBean : project.getClasses())
    {
      total += 1;
    }
    return total;
  }


  private double intraConnectivity(Project project)
  {
    List<PackageBean> packages = new ArrayList<>();
    for(PackageBean aPackage : project.getPackages())
    {
      packages.add(aPackage);
    }
    return CKMetrics.computeMediumIntraConnectivity(packages);
  }

  private double interConnectivity(Project project)
  {
    List<PackageBean> packages = new ArrayList<>();
    for(PackageBean aPackage : project.getPackages())
    {
      packages.add(aPackage);
    }
    return CKMetrics.computeMediumInterConnectivity(packages);
  }

  public ProjectMetricBean calculateProjectMetrics(Project project)
  {
    int loc = totalLOC(project);
    int eloc = totalELOC(project);
    int numOfClasses = totalClasses(project);
    double intraConnectivity = intraConnectivity(project);
    double interConnectivity = interConnectivity(project);
    return new ProjectMetricBean(loc,eloc,numOfClasses,intraConnectivity,interConnectivity);
  }
}
