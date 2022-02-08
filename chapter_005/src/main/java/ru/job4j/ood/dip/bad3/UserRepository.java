package ru.job4j.ood.dip.bad3;

import java.util.Arrays;
import java.util.HashMap;

public class UserRepository {
    private static long countId = 1;
    private HashMap<Long, User> users = new HashMap<>();

    private static long getNextId() {
        return countId++;
    }

    public UserRepository() {
        User[] usersGenerate = new User[]{
                new User(getNextId(), "Ivan"),
                new User(getNextId(), "Oleg"),
                new User(getNextId(), "Kristina")
        };
        this.users.putAll(Arrays.stream(usersGenerate).collect(
                HashMap::new,
                (longUserHashMap, user) -> longUserHashMap.put(user.getId(), user),
                HashMap::putAll
        ));
    }

    public User findBy(long id) {
        return users.get(id);
    }
}
