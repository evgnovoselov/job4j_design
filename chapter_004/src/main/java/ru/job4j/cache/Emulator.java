package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Класс демонстрирующий работу кеша.
 */
public class Emulator {
    private AbstractCache<String, String> cache = new DirFileCache("./files/chapter_004/cache");
    private Scanner scanner = new Scanner(System.in);


    private void run() {
        boolean run = true;
        int action;
        while (run) {
            showMenu();
            System.out.print("Выберите пункт меню: ");
            action = scanner.nextInt();
            if (action == 0) {
                run = false;
            }
            if (action == 1) {
                changeDirCache();
            }
        }
    }

    private void changeDirCache() {
        System.out.print("Введите путь к директории: ");
        String dir = scanner.nextLine();
        if (Files.isDirectory(Path.of(dir))) {
            cache = new DirFileCache(dir);
        }

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
