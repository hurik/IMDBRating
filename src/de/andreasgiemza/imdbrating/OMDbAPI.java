package de.andreasgiemza.imdbrating;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hurik
 */
public final class OMDbAPI {

    private OMDbAPI() {
    }

    public static void getData(Movie movie) {
        try {
            InputStream input = new URL("http://www.omdbapi.com/?i=" + URLEncoder.encode(movie.getImdbID(), "UTF-8")).openStream();
            Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
            }.getType());

            movie.setImdbName(map.get("Title"));
            movie.setImdbRating(Double.parseDouble(map.get("imdbRating")));
            movie.setImdbVotesCount(Long.parseLong(map.get("imdbVotes").replace(",", "")));
            movie.setImdbGenre(Arrays.asList(map.get("Genre").split(",")));
            movie.setImdbCountries(Arrays.asList(map.get("Country").split(",")));
        } catch (IOException ex) {
            Logger.getLogger(OMDbAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
