package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Класс демонстрирующий работу кеша.
 */
public class Emulator {
    private AbstractCache<String, String> cache = new DirFileCache("./files/chapter_004/cache");
    private final Scanner scanner = new Scanner(System.in);

    private void run() {
        boolean run = true;
        int action;
        while (run) {
            showMenu();
            System.out.print("Выберите пункт меню: ");
            action = Integer.parseInt(scanner.nextLine());
            if (action == 0) {
                run = false;
            }
            if (action == 1) {
                changeDirCache();
            }
            if (action == 2) {
                putInCache();
            }
            if (action == 3) {
                showCache();
            }
        }
    }

    private void changeDirCache() {
        System.out.print("Введите путь к директории: ");
        String dir = scanner.nextLine();
        if (Files.isDirectory(Path.of(dir))) {
            cache = new DirFileCache(dir);
            System.out.printf("Установлена данная директория: %s%n", dir);
        } else {
            System.out.printf("Ошибка данной директории не существует: %s%n", dir);
        }
    }

    private void putInCache() {
        System.out.print("Введите имя файла для добавление в кеш: ");
        String file = scanner.nextLine();
        cache.put(file, null);
        cache.get(file);
    }

    private void showCache() {
        System.out.print("Введите имя файла для получения кеша: ");
        String file = scanner.nextLine();
        System.out.println(cache.get(file));
    }

    private void showMenu() {
        System.out.println("=== Меню");
        System.out.println("=== 1. Выбрать директорию");
        System.out.println("=== 2. Добавить файл в кеш");
        System.out.println("=== 3. Получить содержимое файла");
        System.out.println("=== 0. Выйти из программы");
    }

    public static void main(String[] args) {
        new Emulator().run();
    }
}
