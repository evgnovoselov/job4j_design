package ru.job4j.io;

import java.io.File;

/**
 * Класс для работы с директорией.
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\Program Files");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subFile : file.listFiles()) {
            System.out.printf("%s %s%n", subFile.getName(), subFile.getTotalSpace());
        }
    }
}
