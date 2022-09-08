package Implementation;

import Messages.FeedbackProducer;
import Messages.RequestHandler;
import Model.DetectRequest;
import Model.DetectResult;
import api.Detecter;
import com.google.gson.Gson;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetectHandler implements RequestHandler {

  public static final String VOLUME = "/results";
  private FeedbackProducer producer;

  public DetectHandler(FeedbackProducer producer) {
    this.producer = producer;
  }

  @Override
  public void handle(String operation)
  {
    Gson gson = new Gson();
    DetectRequest request = gson.fromJson(operation,DetectRequest.class);
    System.out.println(request.getBuildPath());
    System.out.println(request.getRepositoryName());
    System.out.println(request.getRepository());


    try
    {
      String detectPath = Paths.get(VOLUME,request.getRepositoryName(),"DETECT").toString();
      new File(detectPath).mkdirs();
      String buildPath = Paths.get(VOLUME,request.getBuildPath()).toString();
      String[] commits = new File(buildPath).list();
      List<Callable<Boolean>> callables = new ArrayList<>();
      Detecter detecter = new Detecter();
      for(String commit : commits)
      {
        System.out.println(commit);
        String pathOfBuild = Paths.get(VOLUME,request.getBuildPath(),commit).toString();
        String pathOfOutput = Paths.get(detectPath,commit + ".xml").toString();
        detecter.detect(pathOfBuild,pathOfOutput);

        Callable<Boolean> c = new RepoDetecter(pathOfBuild,pathOfOutput);
        callables.add(c);
      }
      //ExecutorService service = Executors.newCachedThreadPool();
      //service.invokeAll(callables);

      DetectResult result = new DetectResult(Paths.get(request.getRepositoryName(),"DETECT").toString());
      producer.sendSuccess(result,request.getRepository());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      DetectResult result = new DetectResult("");

      producer.sendFailure(result,request.getRepository());
    }

  }
}
