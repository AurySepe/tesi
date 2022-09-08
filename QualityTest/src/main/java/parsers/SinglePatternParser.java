package parsers;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public abstract class SinglePatternParser<T> implements Parser<T>{
  @Override
  public T getResult(XMLEventReader eventReader) {
    while (eventReader.hasNext()) {
      XMLEvent nextEvent = null;
      try {
        nextEvent = eventReader.nextEvent();
      } catch (XMLStreamException e) {
        return null;
      }
      if (nextEvent.isStartElement()) {
        StartElement startElement = nextEvent.asStartElement();
        processStartElement(startElement);
      }
      if (nextEvent.isEndElement()) {
        EndElement endElement = nextEvent.asEndElement();
        if(processEndElement(endElement))
        {
          break;
        }

      }
    }
    T result = createResult();
    reset();
    return result;
  }

  protected abstract void reset();

  protected abstract boolean processEndElement(EndElement endElement);

  protected abstract T createResult();

  public abstract void processStartElement(StartElement startElement);
}
