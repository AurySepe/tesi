import history.HistoryClassIdentifier;
import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;
import it.unisa.codeSmellAnalyzer.computation.FolderToJavaProjectConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
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

public class Main {
  public static void main(String[] args)  {

    String projectSourcesPath = "C:\\Users\\aurel\\Desktop\\Lavoro tesi\\Jedit\\Jedit 4.3\\src";

    String patternsPath = "C:\\Users\\aurel\\Desktop\\Lavoro tesi\\Jedit\\Jedit 4.3\\patterns.xml";

    String historyPath = "";

    String outputPath = "C:\\Users\\aurel\\Desktop\\Lavoro tesi\\Jedit\\Jedit 4.3";

    try {
      File projectPath = new File(projectSourcesPath);
      Vector<PackageBean> packages =
          FolderToJavaProjectConverter.convert(projectPath.getAbsolutePath());
      Project project = new NormalProject(packages);
//      JsonReader reader = new JsonReader(new FileReader(historyPath));
//      Gson gson = new Gson();
//      Type mapType = new TypeToken<Map<String, Integer>>() {
//      }.getType();
//      Map<String, Integer> nameToId = gson.fromJson(reader, mapType);
//
//      HistoryClassIdentifier classIdentifier = new MapHistoryClassIdentifier(nameToId);
      HistoryClassIdentifier classIdentifier = new HistoryClassIdentifier() {
        @Override
        public int getHystoryId(String fullName) {
          return -1;
        }
      };

      ProjectMetricCalculator metricCalculator = new ProjectMetricCalculator();

      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader readerXml =
          xmlInputFactory.createXMLEventReader(new FileInputStream(patternsPath));

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
      projectWriter.writeProjectData(Paths.get(outputPath,"project.csv").toString());
      projectWriter.writeClassData(Paths.get(outputPath,"classes.csv").toString());
      projectWriter.writeClassSmells(Paths.get(outputPath,"smells.csv").toString());
    } catch (CoreException | FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();

    }

  }
}
