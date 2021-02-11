package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Программа фильтрует файлы по заданному предикату.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "js").forEach(System.out::println);
    }

    /**
     * Метод проходит по дереву и ищет файлы только по определенному предикату.
     *
     * @param root Корень пути.
     * @param ext  Проверочное окончание.
     * @return Список отфильтрованных файлов.
     * @throws IOException Ошибка ввода-вывода.
     */
    private static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(path -> path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
