package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс считывания конфигурации сервиса.
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.putAll(read.lines()
                    .filter(line -> !line.isBlank())
                    .filter(line -> !line.contains("##"))
                    .filter(line -> line.contains("="))
                    .collect(
                            HashMap::new,
                            (map, line) -> {
                                int div = line.indexOf("=");
                                String key = line.substring(0, div).trim();
                                String value = line.substring(div + 1).trim();
                                if (!key.isBlank() && !value.isBlank()) {
                                    map.put(key, value);
                                }
                            },
                            HashMap::putAll
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("files/app.properties"));
    }
}
