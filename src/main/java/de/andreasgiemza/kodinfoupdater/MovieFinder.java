package de.andreasgiemza.kodinfoupdater;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author hurik
 */
public class MovieFinder extends SimpleFileVisitor<Path> {

    private final LinkedList<Movie> movies;
    private final Gui gui;
    private int movieCount = 0;
    private boolean nfoFound = false;
    private final List<Path> noNfoPaths;
    private final ExecutorService executor;

    MovieFinder(LinkedList<Movie> movies, Gui gui, List<Path> noNfoPaths, ExecutorService executor) {
        this.movies = movies;
        this.gui = gui;
        this.noNfoPaths = noNfoPaths;
        this.executor = executor;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes bfa) throws IOException {
        if (file.getFileName().toString().endsWith(".nfo")) {
            Movie newMovie = NFO.readNfo(file);

            if (newMovie != null) {
                newMovie.getIMDBData(executor);
                nfoFound = true;
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
        nfoFound = false;

        if (dir.getFileName().toString().startsWith(".")) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (!nfoFound) {
            noNfoPaths.add(dir);
        }

        return super.postVisitDirectory(dir, exc);
    }
}
