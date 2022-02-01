package ru.job4j.menu;

/**
 * Реализация принтера, который добавляет черточки и отступы для подразделов.
 */
public class LineMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            System.out.printf("%s %s%n", menuItemInfo.getNumber(), menuItemInfo.getName());
        });
    }
}
