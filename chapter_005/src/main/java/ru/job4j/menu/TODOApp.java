package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TODOApp {
    public static final String ROOT = null;
    private List<Task> rootTasks = new ArrayList<>();

    public boolean add(String parentName, String childName) {
        boolean root = Objects.equals(ROOT, parentName);
        boolean result = false;
        if (root) {
            result = rootTasks.add(new Task(childName));
        }
        return result;
    }

    public void print() {
        System.out.println();
    }

    private static class Task {
        private static long countId = 1;
        private final long id = countId++;
        private final String name;
        private final List<Task> children = new ArrayList<>();

        public Task(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<Task> getChildren() {
            return children;
        }
    }
}
