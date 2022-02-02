package ru.job4j.menu;

/**
 * Реализация принтера, который добавляет черточки и отступы для подразделов.
 */
public class LineMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            int deepItem = menuItemInfo.getNumber().split("\\.").length - 1;
            System.out.printf(
                    "%s%s%n",
                    deepItem > 0 ? String.format("%s ", "----".repeat(deepItem)) : "",
                    menuItemInfo.getName()
            );
        });
    }
}
