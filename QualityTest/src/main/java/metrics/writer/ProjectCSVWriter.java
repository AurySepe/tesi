package metrics.writer;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import metrics.calculators.ClassMetricCalculator;
import metrics.calculators.ClassSmellCalculator;
import metrics.calculators.ProjectMetricCalculator;
import metrics.writer.beans.ClassMetricsBean;
import metrics.writer.beans.ClassSmellBeans;
import metrics.writer.beans.ProjectMetricBean;
import patterns.information.DesignPatternInformationFinder;
import structures.Project;

public class ProjectCSVWriter extends ProjectWriter{


  private ProjectMetricCalculator projectMetricCalculator;

  private ClassMetricCalculator classMetricCalculator;

  private ClassSmellCalculator classSmellCalculator;


  public ProjectCSVWriter(Project project,
                          ProjectMetricCalculator projectMetricCalculator,
                          ClassMetricCalculator classMetricCalculator,
                          ClassSmellCalculator classSmellCalculator) {
    super(project);
    this.projectMetricCalculator = projectMetricCalculator;
    this.classMetricCalculator = classMetricCalculator;
    this.classSmellCalculator = classSmellCalculator;
  }

  @Override
  public void writeProjectData(String outputPath) {
    try {
      Writer writer = new FileWriter(outputPath);
      StatefulBeanToCsv<ProjectMetricBean> beanToCsv = new StatefulBeanToCsvBuilder<ProjectMetricBean>(writer).withSeparator('\t').withApplyQuotesToAll(false).build();

      beanToCsv.write(projectMetricCalculator.calculateProjectMetrics(getProject()));
      writer.close();
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void writeClassData(String outputPath) {
    try {
      Writer writer = new FileWriter(outputPath);
      StatefulBeanToCsv<ClassMetricsBean> beanToCsv = new StatefulBeanToCsvBuilder<ClassMetricsBean>(writer).withSeparator('\t').withApplyQuotesToAll(false).build();
      for(ClassBean classBean : getProject().getClasses())
      {
        beanToCsv.write(classMetricCalculator.calculateMetrics(classBean));
      }
      writer.close();
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeClassSmells(String outputPath) {
    try {
      Writer writer = new FileWriter(outputPath);
      StatefulBeanToCsv<ClassSmellBeans> beanToCsv = new StatefulBeanToCsvBuilder<ClassSmellBeans>(writer).withSeparator('\t').withApplyQuotesToAll(false).build();
      for(ClassBean classBean : getProject().getClasses())
      {
        beanToCsv.write(classSmellCalculator.calculateSmell(classBean));
      }
      writer.close();
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeMethodData(String outputPath) {

  }
}
