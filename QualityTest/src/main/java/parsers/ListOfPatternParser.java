package parsers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import patterns.Singleton;

public class ListOfPatternParser<T> implements Parser<List<T>>{

  private Parser<T> parser;

  public ListOfPatternParser(Parser<T> parser) {
    this.parser = parser;
  }

  @Override
  public List<T> getResult(XMLEventReader eventReader) {
    List<T> patterns = new ArrayList<>();
    while (eventReader.hasNext()) {
      XMLEvent nextEvent = null;
      try {
        nextEvent = eventReader.nextEvent();
      } catch (XMLStreamException e) {
        return null;
      }
      if (nextEvent.isStartElement()) {
        StartElement startElement = nextEvent.asStartElement();
        if (startElement.getName().getLocalPart().equals("instance")) {
          T result = parser.getResult(eventReader);
          if (result != null) {
            patterns.add(result);
          }
        }
      }
      if (nextEvent.isEndElement()) {
        EndElement endElement = nextEvent.asEndElement();
        if (endElement.getName().getLocalPart().equals("pattern")) {
          break;
        }
      }
    }
    return patterns;
  }
}
