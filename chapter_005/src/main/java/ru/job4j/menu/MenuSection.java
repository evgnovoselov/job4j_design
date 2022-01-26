package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuSection {
    private String name;
    private final List<MenuSection> subSections = new ArrayList<>();

    public MenuSection(String name) {
        this.name = name;
    }

    public boolean addSubSection(MenuSection section) {
        return subSections.add(section);
    }
}
