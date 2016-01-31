package de.andreasgiemza.kodinfoupdater;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author hurik
 */
public final class NFO {

    private NFO() {
    }

    public static Movie readNfo(Path nfo) {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(nfo.toFile());
            Element rootNode = document.getRootElement();

            return new Movie(
                    nfo,
                    rootNode.getChildText("id"),
                    rootNode.getChildText("title"),
                    rootNode.getChildText("rating"),
                    rootNode.getChildText("votes"),
                    rootNode.getChildren("genre"),
                    rootNode.getChildren("country"),
                    rootNode.getChildText("year"));
        } catch (IOException | JDOMException ex) {
            return null;
        }
    }

    public static void updateNfo(Movie movie, boolean ignoreOlderRatings) {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(movie.getNfo().toFile());
            Element rootNode = document.getRootElement();

            if (!ignoreOlderRatings || movie.getLocalVotesCount() <= movie.getImdbVotesCount()) {
                rootNode.getChild("rating").setText(Double.toString(movie.getImdbRating()));
                rootNode.getChild("votes").setText(Long.toString(movie.getImdbVotesCount()));
            }

            rootNode.removeChildren("genre");
            for (String genre : movie.getImdbGenre()) {
                Element child = new Element("genre").setText(genre);
                rootNode.addContent(child);
            }

            rootNode.removeChildren("country");
            for (String country : movie.getImdbCountries()) {
                Element child = new Element("country").setText(country);
                rootNode.addContent(child);
            }

            rootNode.getChild("year").setText(Integer.toString(movie.getImdbYear()));

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileOutputStream(movie.getNfo().toFile()));
        } catch (IOException | JDOMException ex) {
            Logger.getLogger(NFO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
