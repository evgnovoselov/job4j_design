package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuSection> menuSections = new ArrayList<>();

    public boolean addMenuSection(MenuSection section) {
        return menuSections.add(section);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuSections=" + menuSections +
                '}';
    }
}
