package ru.job4j.io.find;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;

/**
 * Класс для работы с аргументами запуска программы.
 * Получает значение аргументов.
 */
public class Arguments {
    private final String[] args;

    public Arguments(String[] args) {
        this.args = args;
    }

    /**
     * Возвращаем директорию из аргумента запуска -d.
     *
     * @return Директория.
     */
    public String directory() {
        return getArgumentValue("-d", args);
    }

    /**
     * Возвращаем аргумент имени для поиска -n.
     *
     * @return Имя.
     */
    public String name() {
        return getArgumentValue("-n", args);
    }

    /**
     * Возвращаем тип имения для поиска -t.
     *
     * @return Тип имени поиска.
     */
    public String type() {
        return getArgumentValue("-t", args);
    }

    /**
     * Возвращаем значение аргумента файла вывода результата -o.
     *
     * @return Путь записи результата в файл.
     */
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

    /**
     * Проверка параметров запуска.
     *
     * @param types Поддерживаемые типы имени файла.
     */
    public void validate(Set<String> types) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Error argument count. Need to set: -d=<directory search> -n=<file name or mask or regex> -t=<type: mask, name, regex> -o=<output file>");
        }
        if (!Files.isDirectory(Path.of(directory()))) {
            throw new IllegalArgumentException(String.format("Error! Not directory: %s", directory()));
        }
        if (!types.contains(type())) {
            throw new IllegalArgumentException(String.format("Error! Not valid type argument: %s, need one of these: %s", type(), types));
        }
        if (!Files.isWritable(Path.of(output()))) {
            throw new IllegalArgumentException(String.format("Error! Not writable in %s", output()));
        }
    }
}
