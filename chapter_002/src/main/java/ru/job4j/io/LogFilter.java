package ru.job4j.io;

import java.util.List;

/**
 * Класс для фильтрации лога на наличие ошибки 404
 */
public class LogFilter {
    public static List<String> filter(String file) {
        return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("files/log.txt");
        System.out.println(log);
    }
}
