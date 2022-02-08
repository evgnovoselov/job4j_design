package ru.job4j.menu;

public class TODOApp {
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
}
