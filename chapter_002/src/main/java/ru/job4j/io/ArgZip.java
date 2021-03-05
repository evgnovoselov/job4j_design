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
        String exclude = exclude();
        String output = output();
        return false;
    }

    public String directory() {
        System.out.println(args[0]);
        if (!"-d=".startsWith(args[0])) {
            throw new IllegalArgumentException("Error program argument -d");
        }
        return null;
    }

    public String exclude() {
        System.out.println(args[1]);
        if (!"-e=".startsWith(args[1])) {
            throw new IllegalArgumentException("Error program argument -e");
        }
        return null;
    }

    public String output() {
        System.out.println(args[2]);
        if (!"-o=".startsWith(args[2])) {
            throw new IllegalArgumentException("Error program argument -o");
        }
        return null;
    }
}
