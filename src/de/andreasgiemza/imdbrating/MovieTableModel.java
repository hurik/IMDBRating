package de.andreasgiemza.imdbrating;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hurik
 */
public class MovieTableModel extends AbstractTableModel {

    private final LinkedList<Movie> movies;
    private final List<String> columnNames = Arrays.asList(
            "IMDB ID",
            "Local title",
            "Local rating",
            "Local votes",
            "Local genres",
            "Local countries",
            "IMDB title",
            "IMDB rating",
            "IMDB votes",
            "IMDB genres",
            "IMDB countries");

    public MovieTableModel(LinkedList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int i, int i1) {
        if (i1 == 0) {
            return movies.get(i).getImdbID();
        } else if (i1 == 1) {
            return movies.get(i).getLocalName();
        } else if (i1 == 2) {
            return movies.get(i).getLocalRating();
        } else if (i1 == 3) {
            return movies.get(i).getLocalVotesCount();
        } else if (i1 == 4) {
            return movies.get(i).getLocalGenres();
        } else if (i1 == 5) {
            return movies.get(i).getLocalCountries();
        } else if (i1 == 6) {
            return movies.get(i).getImdbName();
        } else if (i1 == 7) {
            return movies.get(i).getImdbRating();
        } else if (i1 == 8) {
            return movies.get(i).getImdbVotesCount();
        } else if (i1 == 9) {
            return movies.get(i).getImdbGenre();
        } else if (i1 == 10) {
            return movies.get(i).getImdbCountries();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);
    }
}
