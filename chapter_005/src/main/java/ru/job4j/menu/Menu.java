package ru.job4j.menu;

import java.util.List;

public class Menu {
    private List<MenuSection> menuSections;

    public boolean addMenuSection(MenuSection section) {
        return menuSections.add(section);
    }

    /**
     * TODO method.
     */
    void printMenu() {
    }
}
