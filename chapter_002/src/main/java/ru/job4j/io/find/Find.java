package ru.job4j.io.find;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Класс ищет файл в заданном каталоге и подкаталогах.
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 */
public class Find {
    public static void main(String[] args) {
        Set<String> types = Set.of("mask", "name", "regex");
        Arguments arguments = new Arguments(args);
        arguments.validate(types);
        write(arguments.output(), search(arguments.directory(), arguments.type(), arguments.name()));
    }

    /**
     * Класс выполняет поиск файлов по директории и поддиректории.
     *
     * @param directory Директория.
     * @param type      Тип записи имени файла.
     * @param name      Имя файла.
     * @return Список путей к файлам.
     */
    public static List<Path> search(String directory, String type, String name) {
        Visitor visitor = new Visitor(path -> false);
        if (type.equals("name")) {
            visitor = new Visitor(path -> path.getFileName().toString().equals(name));
        }
        if (type.equals("regex")) {
            Pattern pattern = Pattern.compile(name);
            visitor = new Visitor(path -> pattern.matcher(path.getFileName().toString()).matches());
        }
        if (type.equals("mask")) {
            Pattern pattern = Pattern.compile(maskToRegex(name));
            visitor = new Visitor(path -> pattern.matcher(path.getFileName().toString()).matches());
        }
        try {
            Files.walkFileTree(Path.of(directory), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getPaths();
    }

    /**
     * Записываем пути к файлам в файл.
     *
     * @param output Путь к файлу для записи.
     * @param paths  Пути к файлам.
     */
    public static void write(String output, List<Path> paths) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(output))) {
            for (Path file : paths) {
                writer.write(file.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод переводит маску в регулярное вырожение.
     *
     * @param text Текст для перевода.
     * @return Регулярное выражение.
     */
    private static String maskToRegex(String text) {
        StringBuilder reg = new StringBuilder();
        for (byte b : text.getBytes(StandardCharsets.UTF_8)) {
            char ch = (char) b;
            if (ch == '*') {
                reg.append(".*");
                continue;
            }
            if (ch == '.') {
                reg.append("\\.");
                continue;
            }
            if (ch == '?') {
                reg.append(".");
                continue;
            }
            reg.append(ch);
        }
        return reg.toString();
    }
}
