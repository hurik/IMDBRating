package de.andreasgiemza.kodinfoupdater.movietable;

import de.andreasgiemza.kodinfoupdater.Movie;
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

        if (isSelected) {
            c.setBackground(Color.decode("0x3399FF"));
        } else {
            c.setBackground(Color.WHITE);
        }

        if (movie.getImdbName() != null) {
            if ((column == 2 || column == 8) && !Objects.equals(movie.getLocalRating(), movie.getImdbRating())) {
                c.setBackground(Color.decode("0xFF4242"));
            }

            if ((column == 3 || column == 9) && !Objects.equals(movie.getLocalVotesCount(), movie.getImdbVotesCount())) {
                c.setBackground(Color.decode("0xFF4242"));
            }

            if ((column == 4 || column == 10) && !Objects.equals(movie.getLocalGenres(), movie.getImdbGenre())) {
                c.setBackground(Color.decode("0xFF4242"));
            }
            if ((column == 5 || column == 11) && !Objects.equals(movie.getLocalCountries(), movie.getImdbCountries())) {
                c.setBackground(Color.decode("0xFF4242"));
            }
            if ((column == 6 || column == 12) && !Objects.equals(movie.getLocalYear(), movie.getImdbYear())) {
                c.setBackground(Color.decode("0xFF4242"));
            }
        }

        return c;
    }
}
