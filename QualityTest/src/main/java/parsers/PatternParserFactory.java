package parsers;

import java.util.HashMap;
import java.util.Map;
import patterns.Adapter;
import patterns.Bridge;
import patterns.Command;
import patterns.Decorator;
import patterns.DesignPattern;
import patterns.FactoryMethod;
import patterns.Observer;
import patterns.Proxy;
import patterns.Singleton;
import patterns.State;
import patterns.Strategy;
import patterns.TemplateMethod;
import structures.Project;

public class PatternParserFactory {

  public PatternsParser newPatternParser(Project project)
  {
    Map<String,ListOfPatternParser<? extends DesignPattern>> patternParserMap = new HashMap<>();

    FactoryMethodParser factoryMethodParser = new FactoryMethodParser(project);
    ListOfPatternParser<FactoryMethod> factoryMethodListOfPatternParser =
        new ListOfPatternParser<>(factoryMethodParser);

    patternParserMap.put(FactoryMethod.TYPE,factoryMethodListOfPatternParser);

    SingletonParser singletonParser = new SingletonParser(project);
    ListOfPatternParser<Singleton> singletonListOfPatternParser =
        new ListOfPatternParser<>(singletonParser);

    patternParserMap.put(Singleton.TYPE,singletonListOfPatternParser);

    AdapterParser adapterParser = new AdapterParser(project);
    ListOfPatternParser<Adapter> adapterListOfPatternParser =
        new ListOfPatternParser<>(adapterParser);

    patternParserMap.put(Adapter.TYPE,adapterListOfPatternParser);

    DecoratorParser decoratorParser = new DecoratorParser(project);
    ListOfPatternParser<Decorator> decoratorListOfPatternParser =
        new ListOfPatternParser<>(decoratorParser);

    patternParserMap.put(Decorator.TYPE,decoratorListOfPatternParser);

    ProxyParser proxyParser = new ProxyParser(project);
    ListOfPatternParser<Proxy> proxyListOfPatternParser = new ListOfPatternParser<>(proxyParser);

    patternParserMap.put(Proxy.TYPE,proxyListOfPatternParser);

    StateParser stateParser = new StateParser(project);
    ListOfPatternParser<State> stateListOfPatternParser = new ListOfPatternParser<>(stateParser);

    patternParserMap.put(State.TYPE,stateListOfPatternParser);

    TemplateMethodParser templateMethodParser = new TemplateMethodParser(project);
    ListOfPatternParser<TemplateMethod> templateMethodListOfPatternParser =
        new ListOfPatternParser<>(templateMethodParser);

    patternParserMap.put(TemplateMethod.TYPE,templateMethodListOfPatternParser);

    BridgeParser bridgeParser = new BridgeParser(project);
    ListOfPatternParser<Bridge> bridgeListOfPatternParser = new ListOfPatternParser<>(bridgeParser);

    patternParserMap.put(Bridge.TYPE,bridgeListOfPatternParser);

    CommandParser commandParser = new CommandParser(project);
    ListOfPatternParser<Command> commandListOfPatternParser = new ListOfPatternParser<>(commandParser);

    patternParserMap.put(Command.TYPE,commandListOfPatternParser);

    ObserverParser observerParser = new ObserverParser(project);
    ListOfPatternParser<Observer> observerListOfPatternParser = new ListOfPatternParser<>(observerParser);

    patternParserMap.put(Observer.TYPE,observerListOfPatternParser);

    StrategyParser strategyParser = new StrategyParser(project);
    ListOfPatternParser<Strategy> strategyListOfPatternParser = new ListOfPatternParser<>(strategyParser);

    patternParserMap.put(Strategy.TYPE,strategyListOfPatternParser);

    return new PatternsParser(patternParserMap);


  }


}
