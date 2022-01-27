package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private final List<MenuSection> menuSections = new ArrayList<>();

    public boolean addMenuSection(MenuSection section) {
        return menuSections.add(section);
    }

    @Override
    public String toString() {
        return String.format("Menu:%n%s",
                menuSections.stream()
                        .map(section -> String.format("%s", section))
                        .collect(Collectors.joining()));
    }
}
