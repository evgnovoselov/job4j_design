package ru.job4j.io.find;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FindFile {
    public static List<Path> search(String directory, String type, String name) {
        Visitor visitor = new Visitor(path -> false);
        if (type.equals("name")) {
            visitor = new Visitor(path -> path.getFileName().toString().equals(name));
        }
        if (type.equals("regex")) {
            visitor = new Visitor(path -> path.getFileName().toString().matches(name));
        }
        if (type.equals("mask")) {
            visitor = new Visitor(path -> path.getFileName().toString().matches(maskToRegex(name)));
        }
        try {
            Files.walkFileTree(Path.of(directory), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getPaths();
    }

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
