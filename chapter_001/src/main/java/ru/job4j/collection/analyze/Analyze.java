package ru.job4j.collection.analyze;

import java.util.List;

/**
 * Класс анализатора.
 */
public class Analyze {
    /**
     * Определении разницы между списками.
     *
     * @param previous прошлый список.
     * @param current  текущий список.
     * @return разница.
     */
    public static Info diff(List<User> previous, List<User> current) {
        return null;
    }

    public static class User {
        private static int counter;
        int id = ++counter;
        String name;

        public User(String name) {
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
