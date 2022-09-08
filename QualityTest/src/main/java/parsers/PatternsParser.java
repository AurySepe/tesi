package parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import patterns.DesignPattern;

public class PatternsParser implements Parser<Map<String,List<? extends DesignPattern>>> {

  private Map<String,ListOfPatternParser<? extends DesignPattern>> parsers;

  public PatternsParser(Map<String, ListOfPatternParser<? extends DesignPattern>> parsers) {
    this.parsers = parsers;
  }

  @Override
  public Map<String,List<? extends DesignPattern>> getResult(XMLEventReader eventReader) {
    Map<String,List<? extends DesignPattern>> patterns = new HashMap<>();
    while (eventReader.hasNext()) {
      XMLEvent nextEvent = null;
      try {
        nextEvent = eventReader.nextEvent();
      } catch (XMLStreamException e) {
        return null;
      }
      if (nextEvent.isStartElement()) {
        StartElement startElement = nextEvent.asStartElement();
        if (startElement.getName().getLocalPart().equals("pattern")) {
          String patternType = startElement.getAttributeByName(new QName("name")).getValue();
          ListOfPatternParser<? extends DesignPattern> listParser = parsers.get(patternType);
          if(listParser != null)
          {
            List<? extends DesignPattern> patternsList = listParser.getResult(eventReader);
            patterns.put(patternType, patternsList);

          }

        }
      }
      if (nextEvent.isEndElement()) {
      }
    }

    return patterns;
  }
}
