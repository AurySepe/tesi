import api.Detecter;

public class TestMain {

  public static void main(String[] args) {
    Detecter detecter = new Detecter();

    String PATHTOBUILD = "C:\\Users\\aurel\\Desktop\\Lavoro tesi\\Jedit\\Jedit 4.3\\Build";

    String OUTPUTPATH = "C:\\Users\\aurel\\Desktop\\Lavoro tesi\\Jedit\\Jedit 4.3\\patterns.xml";

    detecter.detect(PATHTOBUILD, OUTPUTPATH);
  }
}
