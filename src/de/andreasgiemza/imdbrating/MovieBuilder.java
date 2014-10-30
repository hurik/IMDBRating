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
class MovieBuilder {

    static Movie create(Path file) {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(file.toFile());
            Element rootNode = document.getRootElement();

            return new Movie(
                    file,
                    rootNode.getChildText("id"),
                    rootNode.getChildText("title"),
                    rootNode.getChildText("rating"),
                    rootNode.getChildText("votes"),
                    rootNode.getChildren("genre"),
                    rootNode.getChildren("country"));
        } catch (IOException | JDOMException ex) {
            return null;
        }
    }
}
