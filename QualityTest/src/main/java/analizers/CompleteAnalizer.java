package analizers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import history.HistoryClassIdentifier;
import history.MapHistoryClassIdentifier;
import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;
import it.unisa.codeSmellAnalyzer.computation.FolderToJavaProjectConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import metrics.calculators.ClassMetricCalculator;
import metrics.calculators.ClassSmellCalculator;
import metrics.calculators.ProjectMetricCalculator;
import metrics.writer.ProjectCSVWriter;
import metrics.writer.ProjectWriter;
import org.eclipse.core.runtime.CoreException;
import parsers.PatternParserFactory;
import parsers.PatternsParser;
import patterns.DesignPattern;
import patterns.information.DesignPatternClassBean;
import patterns.information.DesignPatternInformationFinder;
import patterns.information.DesignPatternInformationFinderMap;
import structures.NormalProject;
import structures.Project;

public class CompleteAnalizer {


  public CompleteAnalizer() {
  }

  public boolean analize(String pathToProject, String pathToHistory,String pathToDetect, String pathToProjectOutput, String pathToClassesOutput, String smellOutput) {

    try {
      File projectPath = new File(pathToProject);
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(pathToHistory));
      Type mapType = new TypeToken<Map<String, Integer>>() {
      }.getType();
      Map<String, Integer> nameToId = gson.fromJson(reader, mapType);
      Vector<PackageBean> packages =
          FolderToJavaProjectConverter.convert(projectPath.getAbsolutePath());
      Project project = new NormalProject(packages);

      HistoryClassIdentifier classIdentifier = new MapHistoryClassIdentifier(nameToId);

      ProjectMetricCalculator metricCalculator = new ProjectMetricCalculator();

      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader readerXml =
          xmlInputFactory.createXMLEventReader(new FileInputStream(pathToDetect));

      PatternsParser patternsParser = new PatternParserFactory().newPatternParser(project);
      Map<String, List<? extends DesignPattern>> patterns = patternsParser.getResult(readerXml);
      Map<String, DesignPatternClassBean> designPatternInformation = new HashMap<>();
      for (List<? extends DesignPattern> designPatterns : patterns.values()) {
        for (DesignPattern designPattern : designPatterns) {
          for (DesignPatternClassBean designPatternClassBean : designPattern.getClassesAndRoles()) {
            ClassBean classBean = designPatternClassBean.getaClass();
            String fullName = classBean.getBelongingPackage() + "." + classBean.getName();
            designPatternInformation.put(fullName, designPatternClassBean);
          }
        }
      }
      DesignPatternInformationFinder designPatternInformationFinder =
          new DesignPatternInformationFinderMap(designPatternInformation);


      ClassMetricCalculator
          classMetricCalculator =
          new ClassMetricCalculator(classIdentifier, designPatternInformationFinder, project);
      ClassSmellCalculator classSmellCalculator =
          new ClassSmellCalculator(classIdentifier, designPatternInformationFinder);
      ProjectWriter
          projectWriter = new ProjectCSVWriter(project, metricCalculator, classMetricCalculator,
          classSmellCalculator);
      projectWriter.writeProjectData(pathToProjectOutput);
      projectWriter.writeClassData(pathToClassesOutput);
      projectWriter.writeClassSmells(smellOutput);
      return true;
    } catch (CoreException | FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();
      return false;

    }

  }
}
