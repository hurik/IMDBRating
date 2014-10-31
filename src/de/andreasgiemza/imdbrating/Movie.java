package de.andreasgiemza.imdbrating;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import org.jdom2.Element;

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
            if (!genre.getContent().isEmpty()) {
                this.localGenres.add(genre.getContent().get(0).getValue());
            }
        }
        for (Element country : localCountries) {
            if (!country.getContent().isEmpty()) {
                this.localCountries.add(country.getContent().get(0).getValue());
            }
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

    public void setImdbName(String imdbName) {
        this.imdbName = imdbName;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Long getImdbVotesCount() {
        return imdbVotesCount;
    }

    public void setImdbVotesCount(Long imdbVotesCount) {
        this.imdbVotesCount = imdbVotesCount;
    }

    public List<String> getImdbGenre() {
        return imdbGenre;
    }

    public void setImdbGenre(List<String> imdbGenre) {
        this.imdbGenre = imdbGenre;
    }

    public List<String> getImdbCountries() {
        return imdbCountries;
    }

    public void setImdbCountries(List<String> imdbCountries) {
        this.imdbCountries = imdbCountries;
    }
}
