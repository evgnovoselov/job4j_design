package ru.job4j.menu;

import org.junit.Test;

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
}