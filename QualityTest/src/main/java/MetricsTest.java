import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import history.HistoryClassIdentifier;
import history.MapHistoryClassIdentifier;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;
import it.unisa.codeSmellAnalyzer.computation.FolderToJavaProjectConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Vector;
import metrics.calculators.ClassMetricCalculator;
import metrics.calculators.ClassSmellCalculator;
import metrics.calculators.ProjectMetricCalculator;
import metrics.writer.ProjectCSVWriter;
import metrics.writer.ProjectWriter;
import org.eclipse.core.runtime.CoreException;
import structures.NormalProject;
import structures.Project;

public class MetricsTest {
  public static void main(String[] args) throws FileNotFoundException {
    /**String pathToDirectory =
        "C:\\Users\\aurel\\Desktop\\TestSource\\88453711ec1b0e03eb7ba02d42b51fe1330b3a73";
    File projectPath = new File(pathToDirectory);
    String pathToHistory = "C:\\Users\\aurel\\Desktop\\TestSource\\88453711ec1b0e03eb7ba02d42b51fe1330b3a73.json";
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader(pathToHistory));
    Type mapType = new TypeToken<Map<String,Integer>>() {
    }.getType();
    Map<String,Integer> nameToId = gson.fromJson(reader,mapType);


    String projectOutputFile = "C:\\Users\\aurel\\Desktop\\TestSource\\project.csv";
    String classesOutputFile = "C:\\Users\\aurel\\Desktop\\TestSource\\classes.csv";
    String smellOutputFile = "C:\\Users\\aurel\\Desktop\\TestSource\\smells.csv";
    try
    {
      Vector<PackageBean> packages =
          FolderToJavaProjectConverter.convert(projectPath.getAbsolutePath());
      Project project = new NormalProject(packages);

      HistoryClassIdentifier classIdentifier = new MapHistoryClassIdentifier(nameToId);

      ProjectMetricCalculator metricCalculator = new ProjectMetricCalculator();
      ClassMetricCalculator classMetricCalculator = new ClassMetricCalculator(classIdentifier,project);
      ClassSmellCalculator classSmellCalculator = new ClassSmellCalculator(classIdentifier);
      ProjectWriter projectWriter = new ProjectCSVWriter(project,metricCalculator,classMetricCalculator,classSmellCalculator);
      //projectWriter.writeProjectData(projectOutputFile);
      //projectWriter.writeClassData(classesOutputFile);
      projectWriter.writeClassSmells(smellOutputFile);
    } catch (CoreException e)
    {
      e.printStackTrace();
    }
**/
  }
}
