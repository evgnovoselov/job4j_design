package ru.job4j.menu;

public class NumberConsolePrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(item -> System.out.printf("%s %s%n", item.getNumber(), item.getName()));
    }
}
