package de.andreasgiemza.imdbrating;

import java.io.IOException;
import java.nio.file.Path;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author hurik
 */
class MovieFactory {

    static Movie create(Path file) {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(file.toFile());
            Element rootNode = document.getRootElement();

            return new Movie(
                    file,
                    rootNode.getChildText("id").toString(),
                    rootNode.getChildText("title").toString(),
                    rootNode.getChildText("rating").toString(),
                    rootNode.getChildText("votes").toString());
        } catch (IOException | JDOMException ex) {
            return null;
        }
    }
}
