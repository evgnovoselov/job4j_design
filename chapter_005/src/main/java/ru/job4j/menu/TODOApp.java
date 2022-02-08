package ru.job4j.menu;

import java.util.Scanner;

public class TODOApp {
    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        String action;
        do {
            showControl();
            action = scanner.nextLine();
        } while (!action.equals("0"));
    }

    private static void showControl() {
        System.out.printf("%s Control Panel %1$s%n", "=".repeat(10));
        System.out.printf("| %-31s |%n", "1. Вывести меню");
        System.out.printf("| %-31s |%n", "2. Создать пункт меню");
        System.out.printf("| %-31s |%n", "3. Вызов действия по названию");
        System.out.printf("| %-31s |%n", "0. Выйти из программы");
        System.out.println("=".repeat(35));
        System.out.print("Выберите действие: ");
    }
}
