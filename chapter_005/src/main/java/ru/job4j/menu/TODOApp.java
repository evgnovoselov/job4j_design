package ru.job4j.menu;

import java.util.Optional;
import java.util.Scanner;

public class TODOApp {
    private static final String CONSOLE_SPLIT_LINE = "=".repeat(45);

    public static void main(String[] args) {
        Menu tasks = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        int action;
        do {
            showControlPanel();
            action = getAction(scanner);
            if (action == 1) {
                showTasks(tasks);
            }
            if (action == 2) {
                addTaskTo(tasks, scanner);
            }
            if (action == 3) {
                selectDoAction(tasks, scanner);
            }
        } while (action != 0);
    }

    private static void selectDoAction(Menu tasks, Scanner scanner) {
        System.out.println(CONSOLE_SPLIT_LINE);
        System.out.print("Введите название задачи, для запуска действия: ");
        String taskName = scanner.nextLine();
        Optional<Menu.MenuItemInfo> itemInfo = tasks.select(taskName);
        if (itemInfo.isEmpty()) {
            System.out.println("Данной задачи не существует");
        } else {
            itemInfo.get().getActionDelegate().delegate();
        }
        System.out.println(CONSOLE_SPLIT_LINE);
    }

    private static int getAction(Scanner scanner) {
        String number = scanner.nextLine();
        return number.isBlank() ? -1 : Integer.parseInt(number);
    }

    private static void showTasks(Menu tasks) {
        System.out.println(CONSOLE_SPLIT_LINE);
        System.out.println("Вывод всех задач");
        new LineMenuConsolePrinter().print(tasks);
        System.out.println(CONSOLE_SPLIT_LINE);
    }

    /**
     * TODO Выбор действия для задачи.
     */
    private static void addTaskTo(Menu tasks, Scanner scanner) {
        System.out.println(CONSOLE_SPLIT_LINE);
        System.out.println("Добавление новой задачи");
        System.out.print("Введите имя задачи: ");
        String taskName = scanner.nextLine();
        System.out.print("Введите имя родительской задачи: ");
        String parentTaskName = scanner.nextLine();
        if (parentTaskName.isBlank()) {
            parentTaskName = Menu.ROOT;
        }
        if (!taskName.isBlank() && tasks.add(parentTaskName, taskName, () -> System.out.println("Что-то делаем."))) {
            System.out.println("Задача успешно создана.");
        } else {
            System.out.println("Не удалось добавить задачу!");
        }
        System.out.println(CONSOLE_SPLIT_LINE);
    }

    private static void showControlPanel() {
        System.out.printf("%s Control Panel %1$s%n", "=".repeat(15));
        System.out.printf("| %-41s |%n", "1. Вывести задачи");
        System.out.printf("| %-41s |%n", "2. Создать задачу");
        System.out.printf("| %-41s |%n", "3. Вызов действия по названию задачи");
        System.out.printf("| %-41s |%n", "0. Выйти из программы");
        System.out.println(CONSOLE_SPLIT_LINE);
        System.out.print("Выберите действие: ");
    }
}
