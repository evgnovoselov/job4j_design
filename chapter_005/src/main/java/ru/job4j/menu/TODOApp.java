package ru.job4j.menu;

import java.util.Iterator;

public class TODOApp implements Iterable<Menu.MenuItemInfo> {
    private final Menu tasks;

    public TODOApp() {
        this(new SimpleMenu());
    }

    public TODOApp(Menu tasks) {
        this.tasks = tasks;
    }

    public boolean add(String parentName, String childName) {
        return tasks.add(parentName, childName, () -> {
        });
    }

    public Menu getTasks() {
        return tasks;
    }

    @Override
    public Iterator<Menu.MenuItemInfo> iterator() {
        return tasks.iterator();
    }
}
