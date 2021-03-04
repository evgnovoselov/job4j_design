package ru.job4j.io;

/**
 * Класс для работы с входными аргументами.
 * -d - directory
 * -e - exclude
 * -o - output
 */
public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return false;
    }

    public String directory() {
        return null;
    }

    public String exclude() {
        return null;
    }

    public String output() {
        return null;
    }
}
