package history;

import java.util.Map;

public class MapHistoryClassIdentifier implements HistoryClassIdentifier{

  private Map<String,Integer> nameToId;

  public MapHistoryClassIdentifier(Map<String, Integer> nameToId) {
    this.nameToId = nameToId;
  }

  @Override
  public int getHystoryId(String fullName) {
    Integer result = nameToId.get(fullName);
    return result != null ? result : -1;
  }
}
