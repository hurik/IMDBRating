package de.andreasgiemza.imdbrating;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final Integer localYear;
    private String imdbName;
    private Double imdbRating;
    private Long imdbVotesCount;
    private List<String> imdbGenre;
    private List<String> imdbCountries;
    private Integer imdbYear;
    private Boolean changes = false;

    Movie(Path nfo, String imdbID, String localName, String localRating, String localVotesCount, List<Element> localGenres, List<Element> localCountries, String localYear) {
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
        if (!"".equals(localYear)) {
            this.localYear = Integer.parseInt(localYear);
        } else {
            this.localYear = null;
        }

        getIMDBData();
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

    public Integer getLocalYear() {
        return localYear;
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

    public Integer getImdbYear() {
        return imdbYear;
    }

    public Boolean getChanges() {
        return changes;
    }

    private void getIMDBData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream input = new URL("http://www.omdbapi.com/?i=" + URLEncoder.encode(imdbID, "UTF-8")).openStream();
                    Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
                    }.getType());

                    imdbName = map.get("Title");
                    imdbRating = Double.parseDouble(map.get("imdbRating"));
                    imdbVotesCount = Long.parseLong(map.get("imdbVotes").replace(",", ""));
                    imdbGenre = Arrays.asList(map.get("Genre").split(", "));
                    imdbCountries = Arrays.asList(map.get("Country").split(", "));
                    imdbYear = Integer.parseInt(map.get("Year"));

                    changes = !Objects.equals(localRating, imdbRating)
                            || !Objects.equals(localVotesCount, imdbVotesCount)
                            || !Objects.equals(localGenres, imdbGenre)
                            || !Objects.equals(localCountries, imdbCountries)
                            || !Objects.equals(localYear, imdbYear);
                } catch (IOException ex) {
                    Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}
