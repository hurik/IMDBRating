package de.andreasgiemza.imdbrating;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hurik
 */
public class MovieTableModel extends AbstractTableModel {

    private final LinkedList<Movie> movies;
    private final String[] columnNames = {
        "IMDB ID",
        "Local title",
        "local rating",
        "local votes",
        "IMDB title",
        "IMDB rating",
        "IMDB votes"};

    public MovieTableModel(LinkedList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
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
            return movies.get(i).getImdbName();
        } else if (i1 == 5) {
            return movies.get(i).getImdbRating();
        } else if (i1 == 6) {
            return movies.get(i).getImdbVotesCount();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
