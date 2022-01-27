package ru.job4j.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest {
    @Test
    public void whenAddSectionsInMenuAndPrintThenShowMenu() {
        Menu menu = new Menu();
        MenuSection section1 = new MenuSection("Задача 1.");
        MenuSection section2 = new MenuSection("Задача 1.1.");
        MenuSection section3 = new MenuSection("Задача 1.1.1.");
        MenuSection section4 = new MenuSection("Задача 1.1.2.");
        MenuSection section5 = new MenuSection("Задача 1.2.");
        MenuSection section6 = new MenuSection("Задача 2.");
        section1.addSubSection(section2);
        section2.addSubSection(section3);
        section2.addSubSection(section4);
        section1.addSubSection(section5);
        menu.addMenuSection(section1);
        menu.addMenuSection(section6);
        System.out.println(menu);
    }
}