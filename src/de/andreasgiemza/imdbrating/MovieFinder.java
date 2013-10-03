package de.andreasgiemza.imdbrating;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;

/**
 *
 * @author hurik
 */
public class MovieFinder extends SimpleFileVisitor<Path> {

    private final LinkedList<Movie> movies;

    public MovieFinder(LinkedList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes bfa) throws IOException {
        if (file.getFileName().toString().endsWith(".nfo")) {
            Movie newMovie = MovieBuilder.create(file);

            if (newMovie == null) {
                newMovie = MovieBuilder.createAfterSavedAsUft8(file);
            }

            if (newMovie != null) {
                movies.add(newMovie);
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
