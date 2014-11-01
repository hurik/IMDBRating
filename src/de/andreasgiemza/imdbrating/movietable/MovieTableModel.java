package de.andreasgiemza.imdbrating.movietable;

import de.andreasgiemza.imdbrating.Movie;
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
            "Local year",
            "IMDB title",
            "IMDB rating",
            "IMDB votes",
            "IMDB genres",
            "IMDB countries",
            "IMDB year");

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
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return movies.get(row).getImdbID();
            case 1:
                return movies.get(row).getLocalName();
            case 2:
                return movies.get(row).getLocalRating();
            case 3:
                return movies.get(row).getLocalVotesCount();
            case 4:
                return movies.get(row).getLocalGenres();
            case 5:
                return movies.get(row).getLocalCountries();
            case 6:
                return movies.get(row).getLocalYear();
            case 7:
                return movies.get(row).getImdbName();
            case 8:
                return movies.get(row).getImdbRating();
            case 9:
                return movies.get(row).getImdbVotesCount();
            case 10:
                return movies.get(row).getImdbGenre();
            case 11:
                return movies.get(row).getImdbCountries();
            case 12:
                return movies.get(row).getImdbYear();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Long.class;
            case 4:
                return List.class;
            case 5:
                return List.class;
            case 6:
                return Integer.class;
            case 7:
                return String.class;
            case 8:
                return Double.class;
            case 9:
                return Long.class;
            case 10:
                return List.class;
            case 11:
                return List.class;
            case 12:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int col
    ) {
        return columnNames.get(col);
    }

    public Movie getMovieAt(int row) {
        return movies.get(row);
    }
}
