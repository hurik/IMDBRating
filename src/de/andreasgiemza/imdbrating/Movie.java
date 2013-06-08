package de.andreasgiemza.imdbrating;

import com.omertron.imdbapi.ImdbApi;
import com.omertron.imdbapi.model.ImdbMovieDetails;
import java.io.FileWriter;
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
public class Movie {

    private final Path nfo;
    private final String imdbID;
    private final String localName;
    private final String localRating;
    private final String localVotesCount;
    private String imdbName;
    private String imdbRating;
    private String imdbVotesCount;

    public Movie(Path nfo, String imdbID, String localName, String localRating, String localVotesCount) {
        this.nfo = nfo;
        this.imdbID = imdbID;
        this.localName = localName;
        this.localRating = localRating;
        this.localVotesCount = localVotesCount;
    }

    public Path getNfo() {
        return nfo;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getLocalName() {
        return localName;
    }

    public String getLocalRating() {
        return localRating;
    }

    public String getLocalVotesCount() {
        return localVotesCount;
    }

    public String getImdbName() {
        return imdbName;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotesCount() {
        return imdbVotesCount;
    }

    public void getIMDBRating() {
        ImdbMovieDetails imdb = ImdbApi.getFullDetails(imdbID);

        imdbName = imdb.getTitle();
        imdbRating = Float.toString(imdb.getRating());
        imdbVotesCount = Double.toString(imdb.getNumVotes());
    }

    public void updateIMDBRating() {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(nfo.toFile());
            Element rootNode = document.getRootElement();

            rootNode.getChild("rating").setText(imdbRating);
            rootNode.getChild("votes").setText(imdbVotesCount);

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(nfo.toFile()));
        } catch (JDOMException ex) {
            Logger.getLogger(MovieFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
