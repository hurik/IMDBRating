package de.andreasgiemza.imdbrating;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import javax.swing.JProgressBar;
import javax.swing.JTable;

/**
 *
 * @author hurik
 */
public class MovieFinder extends SimpleFileVisitor<Path> {

    private final LinkedList<Movie> movies;
    private final Gui gui;
    private int movieCount = 0;

    MovieFinder(LinkedList<Movie> movies, Gui gui) {
        this.movies = movies;
        this.gui = gui;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes bfa) throws IOException {
        if (file.getFileName().toString().endsWith(".nfo")) {
            Movie newMovie = MovieBuilder.create(file);

            if (newMovie != null) {
                movies.add(newMovie);
                movieCount++;

                gui.updateMovieFinder(movieCount);
            }

            return FileVisitResult.SKIP_SIBLINGS;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes bfa) throws IOException {
        if (dir.endsWith(".actors")) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }
}
