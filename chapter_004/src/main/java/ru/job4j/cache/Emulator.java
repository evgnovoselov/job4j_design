package ru.job4j.cache;

import java.util.Scanner;

/**
 * Класс демонстрирующий работу кеша.
 */
public class Emulator {
    public static void main(String[] args) {
        AbstractCache<String, String> cache = new DirFileCache("./files/chapteer_004/cache");
        System.out.println(cache.get("Names.txt"));
        System.out.println(cache.get("Address.txt"));
        boolean run = true;
        int action;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            action = scanner.nextInt();
            if (action == 0) {
                run = false;
            }
        }
    }

    private static void printMenu() {
        System.out.println("=== Меню");
        System.out.println("=== 1. Выбрать директорию");
        System.out.println("=== 2. Добавить файл в кеш");
        System.out.println("=== 3. Получить содержимое файла");
        System.out.println("=== 0. Выйти из программы");
    }
}
