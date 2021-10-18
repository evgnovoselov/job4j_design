package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO Добавить комментарий.
 */
public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String load = get(key);
        if (load == null) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(String.format("%s/%s", cachingDir, key)))) {
                while (reader.ready()) {
                    stringBuilder.append((char) reader.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            load = stringBuilder.toString();
            put(key, load);
        }
        return load;
    }
}
