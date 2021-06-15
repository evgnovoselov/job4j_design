package ru.job4j.io.find;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

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
        if (args.length < 4) {
            throw new IllegalArgumentException("Error argument count. Need to set: -d=<directory search> -n=<file name or mask or regex> -t=<type: mask, name, regex> -o=<output file>");
        }
        Arguments arguments = new Arguments(args);
        if (!Files.isDirectory(Path.of(arguments.directory()))) {
            throw new IllegalArgumentException(String.format("Error! Not directory: %s", arguments.directory()));
        }
        if (!types.contains(arguments.type())) {
            throw new IllegalArgumentException(String.format("Error! Not valid type argument: %s, need one of these: %s", arguments.type(), types));
        }
        Visitor visitor = new Visitor(path -> false);
        if (arguments.type().equals("name")) {
            visitor = new Visitor(path -> path.getFileName().toString().equals(arguments.name()));
        }
        if (arguments.type().equals("regex")) {
            visitor = new Visitor(path -> path.getFileName().toString().matches(arguments.name()));
        }
        if (arguments.type().equals("mask")) {
            StringBuilder reg = new StringBuilder();
            for (byte b : arguments.name().getBytes(StandardCharsets.UTF_8)) {
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
            visitor = new Visitor(path -> path.getFileName().toString().matches(reg.toString()));
        }
        try {
            Files.walkFileTree(Path.of(arguments.directory()), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(arguments.output()))) {
            for (Path file : visitor.getPaths()) {
                writer.write(file.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
