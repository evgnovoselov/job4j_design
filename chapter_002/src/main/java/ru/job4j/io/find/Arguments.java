package ru.job4j.io.find;

import java.util.Arrays;

/**
 * Класс для работы с аргументами запуска программы.
 * Получает значение аргументов.
 */
public class Arguments {
    private final String[] args;

    public Arguments(String[] args) {
        this.args = args;
    }

    public String directory() {
        return getArgumentValue("-d", args);
    }

    public String name() {
        return getArgumentValue("-n", args);
    }

    public String type() {
        return getArgumentValue("-t", args);
    }

    public String output() {
        return getArgumentValue("-o", args);
    }

    private static String getArgumentValue(String key, String[] args) {
        String argument = Arrays.stream(args)
                .filter(s -> s.startsWith(String.format("%s=", key)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Error program argument %s", key)));
        return argument.substring(key.length() + 1);
    }
}
