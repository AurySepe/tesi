package Implementation;

import api.Detecter;
import java.util.concurrent.Callable;

public class RepoDetecter implements Callable<Boolean> {

  private String pathOfBuild;


  private String pathOfOutput;

  public RepoDetecter(String pathOfBuild, String pathOfOutput) {
    this.pathOfBuild = pathOfBuild;
    this.pathOfOutput = pathOfOutput;
  }


  @Override
  public Boolean call() throws Exception {
    System.out.println("detecting");
    try {
      Detecter detecter = new Detecter();
      detecter.detect(pathOfBuild, pathOfOutput);
      System.out.println("finished detecting");
    }catch (Exception e)
    {
      e.printStackTrace();
    }


    return true;
  }
}
