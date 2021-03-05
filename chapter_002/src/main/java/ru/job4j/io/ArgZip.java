package ru.job4j.io;

import java.util.List;

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
        System.out.println(List.of(args));
        if (args.length < 3) {
            throw new IllegalArgumentException("Usage java -jar zip.jar -d=DIRECTORY -e=EXCLUDE_EXT -o=OUTPUT_ZIP_FILE");
        }
        String directory = directory();
        System.out.println("directory = " + directory);
        String exclude = exclude();
        System.out.println("exclude = " + exclude);
        String output = output();
        System.out.println("output = " + output);
        return false;
    }

    public String directory() {
        return getArgumentValue("-d", args[0]);
    }

    public String exclude() {
        return getArgumentValue("-e", args[1]);
    }

    public String output() {
        return getArgumentValue("-o", args[2]);
    }

    private static String getArgumentValue(String key, String argument) {
        if (!argument.startsWith(String.format("%s=", key))) {
            throw new IllegalArgumentException(String.format("Error program argument %s", key));
        }
        return argument.substring(key.length() + 1);
    }
}
