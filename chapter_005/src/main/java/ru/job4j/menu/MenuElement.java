package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Todo add consumer.
 */
public class MenuElement {
    private int deep = 0;
    private String name;
    private final List<MenuElement> subElements = new ArrayList<>();

    public MenuElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean add(MenuElement section) {
        section.deep = this.deep + 1;
        return subElements.add(section);
    }

    @Override
    public String toString() {
        return String.format("%s%s%n%s",
                deep < 1 ? "" : "----".repeat(deep) + " ",
                name,
                subElements.stream()
                        .map(section -> String.format("%s", section))
                        .collect(Collectors.joining()));
    }
}
