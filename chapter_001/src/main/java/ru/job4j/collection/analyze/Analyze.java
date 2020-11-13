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
    public Info diff(List<User> previous, List<User> current) {
        return null;
    }

    public static class User {
        int id;
        String name;
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
