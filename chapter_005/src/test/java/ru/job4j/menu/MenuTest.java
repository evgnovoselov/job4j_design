package ru.job4j.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest {
    @Test
    public void whenAddSectionsInMenuAndPrintThenShowMenu() {
        Menu menu = new Menu();
        MenuElement element1 = new MenuElement("Задача 1.");
        MenuElement element2 = new MenuElement("Задача 1.1.");
        MenuElement element3 = new MenuElement("Задача 1.1.1.");
        MenuElement element4 = new MenuElement("Задача 1.1.2.");
        MenuElement element5 = new MenuElement("Задача 1.2.");
        MenuElement element6 = new MenuElement("Задача 2.");
        element1.add(element2);
        element2.add(element3);
        element2.add(element4);
        element1.add(element5);
        menu.add(element1);
        menu.add(element6);
        String expected = "Menu:" + System.lineSeparator()
                + "Задача 1." + System.lineSeparator()
                + "---- Задача 1.1." + System.lineSeparator()
                + "-------- Задача 1.1.1." + System.lineSeparator()
                + "-------- Задача 1.1.2." + System.lineSeparator()
                + "---- Задача 1.2." + System.lineSeparator()
                + "Задача 2." + System.lineSeparator();
        assertEquals(expected, menu.toString());
    }
}