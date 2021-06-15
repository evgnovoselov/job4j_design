package ru.job4j.io.find;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс просматривает папки и подпапки в поисках файла по предикату.
 */
public class Visitor extends SimpleFileVisitor<Path> {
    private final Predicate<Path> predicate;
    private final List<Path> paths = new ArrayList<>();

    public Visitor(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate.test(file)) {
            paths.add(file.toAbsolutePath().normalize());
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
