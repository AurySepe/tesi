import it.unisa.codeSmellAnalyzer.beans.ClassBean;
import it.unisa.codeSmellAnalyzer.beans.PackageBean;
import it.unisa.codeSmellAnalyzer.computation.FolderToJavaProjectConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import org.eclipse.core.runtime.CoreException;
import parsers.AdapterParser;
import parsers.DecoratorParser;
import parsers.FactoryMethodParser;
import parsers.ListOfPatternParser;
import parsers.PatternsParser;
import parsers.ProxyParser;
import parsers.SingletonParser;
import parsers.StateParser;
import parsers.TemplateMethodParser;
import patterns.Adapter;
import patterns.Decorator;
import patterns.FactoryMethod;
import patterns.Proxy;
import patterns.Singleton;
import patterns.State;
import patterns.TemplateMethod;
import structures.NormalProject;
import structures.Project;

public class Main {
  public static void main(String[] args) {
// Path to the directory containing all the projects under analysis
    String pathToDirectory =
        "C:\\Users\\aurel\\Desktop\\TestSource\\88453711ec1b0e03eb7ba02d42b51fe1330b3a73";
    File project = new File(pathToDirectory);

    // Output file declaration
    String output =
        "testCase,assertionRoulette,mysteryGuest,eagerTest,sensitiveEquality,lazyTest,resourceOptimistism\n";
    String outputFile = "C:\\Users\\aurel\\Desktop\\TestSource\\output.csv";

    // Declaring Class-level test smell objects.


//    try {
//      // Method to convert a directory into a set of java packages.
//      Vector<PackageBean> packages =
//          FolderToJavaProjectConverter.convert(project.getAbsolutePath());
//      Project project1 = new NormalProject(packages);
//      String pathToXml =
//          "C:\\Users\\aurel\\Desktop\\TestSource\\88453711ec1b0e03eb7ba02d42b51fe1330b3a73.xml";
//      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
//      XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(pathToXml));
//      FactoryMethodParser factoryMethodParser = new FactoryMethodParser(project1);
//      ListOfPatternParser<FactoryMethod> factoryMethodListOfPatternParser =
//          new ListOfPatternParser<>(factoryMethodParser);
//
//      SingletonParser singletonParser = new SingletonParser(project1);
//      ListOfPatternParser<Singleton> singletonListOfPatternParser =
//          new ListOfPatternParser<>(singletonParser);
//
//      AdapterParser adapterParser = new AdapterParser(project1);
//      ListOfPatternParser<Adapter> adapterListOfPatternParser =
//          new ListOfPatternParser<>(adapterParser);
//
//      DecoratorParser decoratorParser = new DecoratorParser(project1);
//      ListOfPatternParser<Decorator> decoratorListOfPatternParser =
//          new ListOfPatternParser<>(decoratorParser);
//
//      ProxyParser proxyParser = new ProxyParser(project1);
//      ListOfPatternParser<Proxy> proxyListOfPatternParser = new ListOfPatternParser<>(proxyParser);
//
//      StateParser stateParser = new StateParser(project1);
//      ListOfPatternParser<State> stateListOfPatternParser = new ListOfPatternParser<>(stateParser);
//
//      TemplateMethodParser templateMethodParser = new TemplateMethodParser(project1);
//      ListOfPatternParser<TemplateMethod> templateMethodListOfPatternParser =
//          new ListOfPatternParser<>(templateMethodParser);
//
//      PatternsParser patternsParser =
//          new PatternsParser(factoryMethodListOfPatternParser, singletonListOfPatternParser,
//              adapterListOfPatternParser, decoratorListOfPatternParser, proxyListOfPatternParser,
//              stateListOfPatternParser, templateMethodListOfPatternParser);
//
//
//      Map<String, List<?>> patterns = patternsParser.getResult(reader);
//      for (String key : patterns.keySet()) {
//        List<?> somePatterns = patterns.get(key);
//        System.out.println(key);
//        for (Object o : somePatterns) {
//          System.out.println(o);
//        }
//      }

//
//    } catch (CoreException | FileNotFoundException | XMLStreamException e) {
//      e.printStackTrace();
//    }
  }

}