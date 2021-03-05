package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс архивации.
 */
public class Zip {
    public void packFiles(List<File> sources, File target) {

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (!new ArgZip(args).valid()) {
            throw new IllegalArgumentException("Usage java -jar zip.jar -d=DIRECTORY -e=EXCLUDE_EXT -o=OUTPUT_ZIP_FILE");
        }
    }
}
