package Messages;

import analizers.CompleteAnalizer;
import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class MetricReleaseAnalizer implements Callable<Boolean> {

  private String release;

  private String metricsPath;

  private String projectPath;

  private String historyPath;

  private String releasesPath;

  private String detectPath;


  public MetricReleaseAnalizer(String release, String metricsPath, String projectPath,
                               String historyPath, String releasesPath, String detectPath) {
    this.release = release;
    this.metricsPath = metricsPath;
    this.projectPath = projectPath;
    this.historyPath = historyPath;
    this.releasesPath = releasesPath;
    this.detectPath = detectPath;
  }

  /**
   * Computes a result, or throws an exception if unable to do so.
   *
   * @return computed result
   * @throws Exception if unable to compute a result
   */
  @Override
  public Boolean call() throws Exception {
    String thisReleaseHistoryPath = Paths.get(historyPath,release + ".json").toString();
    String thisReleaseOutputPath = Paths.get(metricsPath,release).toString();
    new File(thisReleaseOutputPath).mkdir();
    String projectOutputPath = Paths.get(thisReleaseOutputPath,"project.csv").toString();
    String classMetricsPath = Paths.get(thisReleaseOutputPath,"class metrics.csv").toString();
    String classSmellPath = Paths.get(thisReleaseOutputPath,"class smells.csv").toString();
    String thisReleasePath = Paths.get(releasesPath,release).toString();
    String thisReleaseDetectPath = Paths.get(detectPath,release + ".xml").toString();
    CompleteAnalizer analizer = new CompleteAnalizer();
    analizer.analize(thisReleasePath,thisReleaseHistoryPath,thisReleaseDetectPath,projectOutputPath,classMetricsPath,classSmellPath);
    return true;
  }
}
