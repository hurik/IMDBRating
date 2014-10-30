package de.andreasgiemza.imdbrating;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    private final Double localRating;
    private final Long localVotesCount;
    private final List<String> localGenres = new LinkedList<>();
    private final List<String> localCountries = new LinkedList<>();
    private String imdbName;
    private Double imdbRating;
    private Long imdbVotesCount;
    private List<String> imdbGenre;
    private List<String> imdbCountries;

    Movie(Path nfo, String imdbID, String localName, String localRating, String localVotesCount, List<Element> localGenres, List<Element> localCountries) {
        this.nfo = nfo;
        this.imdbID = imdbID;
        this.localName = localName;
        this.localRating = Double.parseDouble(localRating);
        this.localVotesCount = Long.parseLong(localVotesCount);
        for (Element genre : localGenres) {
            this.localGenres.add(genre.getContent().get(0).getValue());
        }
        for (Element country : localCountries) {
            this.localCountries.add(country.getContent().get(0).getValue());
        }
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

    public Double getLocalRating() {
        return localRating;
    }

    public Long getLocalVotesCount() {
        return localVotesCount;
    }

    public List<String> getLocalGenres() {
        return localGenres;
    }

    public List<String> getLocalCountries() {
        return localCountries;
    }

    public String getImdbName() {
        return imdbName;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public Long getImdbVotesCount() {
        return imdbVotesCount;
    }

    public List<String> getImdbGenre() {
        return imdbGenre;
    }

    public List<String> getImdbCountries() {
        return imdbCountries;
    }

    public void getIMDBRating() {
        try {
            InputStream input = new URL("http://www.omdbapi.com/?i=" + URLEncoder.encode(imdbID, "UTF-8")).openStream();
            Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
            }.getType());

            imdbName = map.get("Title");
            imdbRating = Double.parseDouble(map.get("imdbRating"));
            imdbVotesCount = Long.parseLong(map.get("imdbVotes").replace(",", ""));
            imdbGenre = Arrays.asList(map.get("Genre").split(","));
            imdbCountries = Arrays.asList(map.get("Country").split(","));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateIMDBRating() {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(nfo.toFile());
            Element rootNode = document.getRootElement();

            rootNode.getChild("rating").setText(Double.toString(imdbRating));
            rootNode.getChild("votes").setText(Long.toString(imdbVotesCount));

            rootNode.removeChildren("genre");
            rootNode.removeChildren("country");

            for (String genre : imdbGenre) {
                Element child = new Element("genre").setText(genre);
                rootNode.addContent(child);
            }
            for (String country : imdbCountries) {
                Element child = new Element("country").setText(country);
                rootNode.addContent(child);
            }
            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileOutputStream(nfo.toFile()));
        } catch (JDOMException ex) {
            Logger.getLogger(MovieFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
