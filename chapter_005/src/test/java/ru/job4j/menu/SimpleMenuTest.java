package ru.job4j.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин",
                        List.of("Купить продукты"),
                        STUB_ACTION,
                        "1."
                ),
                menu.select("Сходить в магазин").orElseThrow()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты",
                        List.of("Купить хлеб", "Купить молоко"),
                        STUB_ACTION,
                        "1.1."
                ),
                menu.select("Купить продукты").orElseThrow()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку",
                        List.of(),
                        STUB_ACTION,
                        "2."
                ),
                menu.select("Покормить собаку").orElseThrow()
        );
        menu.forEach(menuItemInfo -> System.out.println(menuItemInfo.getNumber() + menuItemInfo.getName()));
    }

    @Test
    public void whenAddAndPrintThenPrintSame() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(mem));
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter printer = new LineMenuConsolePrinter();
        printer.print(menu);
        String expected = "Сходить в магазин" + System.lineSeparator() +
                "---- Купить продукты" + System.lineSeparator() +
                "-------- Купить хлеб" + System.lineSeparator() +
                "-------- Купить молоко" + System.lineSeparator() +
                "Покормить собаку" + System.lineSeparator();
        assertEquals(expected, mem.toString());
        System.setOut(oldOut);
    }
}