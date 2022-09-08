package parsers;

import javax.xml.stream.XMLEventReader;

public interface Parser<T> {

  public T getResult(XMLEventReader eventReader);

}
