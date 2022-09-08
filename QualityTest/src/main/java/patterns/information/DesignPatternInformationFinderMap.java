package patterns.information;

import java.util.Map;

public class DesignPatternInformationFinderMap implements DesignPatternInformationFinder{

  private Map<String,DesignPatternClassBean> designPatternClassBeanMap;

  public DesignPatternInformationFinderMap(
      Map<String, DesignPatternClassBean> designPatternClassBeanMap) {
    this.designPatternClassBeanMap = designPatternClassBeanMap;
  }

  @Override
  public DesignPatternClassBean findDesignPatternClassInformation(String fullClassName) {
    return designPatternClassBeanMap.get(fullClassName);
  }
}
