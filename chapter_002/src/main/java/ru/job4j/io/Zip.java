package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс архивации.
 */
public class Zip {
    /**
     * Архивации директории.
     *
     * @param sources Файлы архивации.
     * @param target  Конечный архив.
     * @param argZip  Аргументы запуска.
     */
    public void packFiles(List<File> sources, File target, ArgZip argZip) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(Paths.get(argZip.directory()).relativize(source.toPath()).toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(in.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Архивация файла.
     *
     * @param source Файл для архивации.
     * @param target Архив файла.
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Usage java -jar zip.jar -d=DIRECTORY -e=EXCLUDE_EXT -o=OUTPUT_ZIP_FILE");
        }
        SearchFiles searchFiles = new SearchFiles(path -> !path.toFile().getName().endsWith(argZip.exclude()));
        try {
            Files.walkFileTree(Paths.get(argZip.directory()), searchFiles);
            new Zip().packFiles(
                    searchFiles.getPaths().stream().map(Path::toFile).collect(Collectors.toList()),
                    new File(argZip.output()),
                    argZip
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
