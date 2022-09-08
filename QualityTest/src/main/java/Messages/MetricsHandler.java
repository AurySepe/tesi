package Messages;

import Model.MetricsRequest;
import Model.MetricsResult;
import com.google.gson.Gson;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MetricsHandler implements RequestHandler{

  private static final String VOLUME = "/results";
  
  private FeedbackProducer feedbackProducer;

  public MetricsHandler(FeedbackProducer feedbackProducer) {
    this.feedbackProducer = feedbackProducer;
  }

  @Override
  public void handle(String operation) {

    Gson gson = new Gson();
    MetricsRequest request = gson.fromJson(operation,MetricsRequest.class);
    try
    {
      String metricsRelativePath = Paths.get(request.getProjectName(),"METRICS").toString();
      String metricsPath = Paths.get(VOLUME,metricsRelativePath).toString();
      String projectPath = Paths.get(VOLUME,request.getProjectPath()).toString();
      String HistoryPath = Paths.get(VOLUME,request.getHistoryPath()).toString();
      String pathToReleases = Paths.get(VOLUME,request.getOrderedReleasePath()).toString();
      String releasesPath = Paths.get(VOLUME,request.getReleasesPath()).toString();
      String detectPath = Paths.get(VOLUME,request.getProjectName(),"DETECT").toString();
      new File(metricsPath).mkdir();
      List<String> releases = Files.readAllLines(Paths.get(pathToReleases));
      List<Callable<Boolean>> callables = new ArrayList<>();
      for (String release : releases)
      {
        MetricReleaseAnalizer analizer = new MetricReleaseAnalizer(release,metricsPath,projectPath,
            HistoryPath,releasesPath,detectPath);
        callables.add(analizer);
      }
      ExecutorService executorService = Executors.newScheduledThreadPool(4);
      executorService.invokeAll(callables);
      MetricsResult metricsResult = new MetricsResult(metricsRelativePath);
      feedbackProducer.sendSuccess(metricsResult,request.getRepositoryOrigin());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      MetricsResult metricsResult = new MetricsResult("");
      feedbackProducer.sendFailure(metricsResult,request.getRepositoryOrigin());
    }
    
  }
}
