package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Класс для работы с входными аргументами.
 * -d - directory
 * -e - exclude
 * -o - output
 */
public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        boolean result = true;
        if (args.length < 3) {
            System.out.println("Usage java -jar zip.jar -d=DIRECTORY -e=EXCLUDE_EXT -o=OUTPUT_ZIP_FILE");
            result = false;
        }
        Path dir = Paths.get(directory());
        if (result && !Files.isDirectory(dir)) {
            System.out.printf("Error! Not directory: %s%n", dir);
            result = false;
        }
        Path out = Paths.get(output());
        if (result && !Files.isDirectory(out.getParent()) && !Files.isWritable(out.getParent())) {
            System.out.printf("Error! Not directory: %s%n", out.getParent());
            result = false;
        }
        return result;
    }

    public String directory() {
        return getArgumentValue("-d", args);
    }

    public String exclude() {
        return getArgumentValue("-e", args);
    }

    public String output() {
        return getArgumentValue("-o", args);
    }

    private static String getArgumentValue(String key, String[] arguments) {
        String argument = Arrays.stream(arguments)
                .filter(s -> s.startsWith(String.format("%s=", key)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Error program argument %s", key)));
        return argument.substring(key.length() + 1);
    }
}
