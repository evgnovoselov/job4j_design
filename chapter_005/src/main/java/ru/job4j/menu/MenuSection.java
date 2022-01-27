package ru.job4j.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Todo add consumer.
 */
public class MenuSection {
    private int deep = 0;
    private String name;
    private final List<MenuSection> subSections = new ArrayList<>();

    public MenuSection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addSubSection(MenuSection section) {
        section.deep = this.deep + 1;
        return subSections.add(section);
    }

    @Override
    public String toString() {
        return String.format("%s%s%n%s",
                deep < 1 ? "" : "----".repeat(deep) + " ",
                name,
                subSections.stream()
                        .map(section -> String.format("%s", section))
                        .collect(Collectors.joining()));
    }
}
