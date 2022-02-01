package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuImpl {
    private final List<MenuElement> menuElements = new ArrayList<>();

    public boolean add(MenuElement element) {
        return menuElements.add(element);
    }

    @Override
    public String toString() {
        return String.format("Menu:%n%s",
                menuElements.stream()
                        .map(element -> String.format("%s", element))
                        .collect(Collectors.joining()));
    }
}
