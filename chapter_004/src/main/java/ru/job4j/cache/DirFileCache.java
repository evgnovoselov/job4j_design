package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Файловый кеш.
 */
public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String load = null;
        Path path = Paths.get(String.format("%s/%s", cachingDir, key));
        if (Files.isReadable(path)) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                while (reader.ready()) {
                    stringBuilder.append((char) reader.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            load = stringBuilder.toString();
        }
        return load;
    }
}
