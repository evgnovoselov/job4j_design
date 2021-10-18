package ru.job4j.cache;

/**
 * TODO Добавить комментарий.
 */
public class Emulator {
    public static void main(String[] args) {
        AbstractCache<String, String> cache = new DirFileCache("./files/chapteer_004/cache");
        cache.load("Names.txt");
        cache.load("Address.txt");
        System.out.println(cache.load("Names.txt"));
        System.out.println(cache.load("Address.txt"));
    }
}
