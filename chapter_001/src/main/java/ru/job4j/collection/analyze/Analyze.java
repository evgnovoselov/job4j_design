package ru.job4j.collection.analyze;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        int changed = 0;
        int comparedCounter = 0;
        var prev = previous.stream().collect(Collectors.toMap(User::getId, user -> user));
        for (var user : current) {
            var prevUser = prev.get(user.getId());
            if (prevUser != null) {
                if (!user.getName().equals(prevUser.getName())) {
                    changed++;
                }
                comparedCounter++;
            }
        }
        return new Info(current.size() - comparedCounter, changed, previous.size() - comparedCounter);
    }

    public static class User {
        private static int counter;
        private int id = ++counter;
        private String name;

        public User(String name) {
            this.name = name;
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        private final int added;
        private final int changed;
        private final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
