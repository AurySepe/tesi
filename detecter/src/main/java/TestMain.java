import api.Detecter;

public class TestMain {

  public static void main(String[] args) {
    Detecter detecter = new Detecter();
    detecter.detect("C:\\Users\\aurel\\Desktop\\TestSource\\java-design-patterns", "C:\\Users\\aurel\\Desktop\\TestSource\\patterns.xml");
  }
}
