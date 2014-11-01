package de.andreasgiemza.imdbrating.movietable;

import de.andreasgiemza.imdbrating.Movie;
import java.awt.Color;
import java.awt.Component;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author hurik
 */
public class MovieTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Movie movie = ((MovieTableModel) table.getModel()).getMovieAt(table.convertRowIndexToModel(row));

        c.setBackground(Color.WHITE);

        if (movie.getImdbName() != null) {
            if (!Objects.equals(movie.getLocalRating(), movie.getImdbRating())
                    || !Objects.equals(movie.getLocalVotesCount(), movie.getImdbVotesCount())
                    || !Objects.equals(movie.getLocalGenres(), movie.getImdbGenre())
                    || !Objects.equals(movie.getLocalCountries(), movie.getImdbCountries())
                    || !Objects.equals(movie.getLocalYear(), movie.getImdbYear())) {
                c.setBackground(Color.decode("0xFF4242"));
            }
        }

        return c;
    }
}
