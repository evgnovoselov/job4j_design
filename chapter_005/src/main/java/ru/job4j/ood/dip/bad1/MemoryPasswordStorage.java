package ru.job4j.ood.dip.bad1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MemoryPasswordStorage {
    private final Map<String, String> passwords = new HashMap<>();

    public boolean add(String name, String password) {
        return passwords.putIfAbsent(name, password) == null;
    }

    public Set<String> getNames() {
        return passwords.keySet();
    }

    public boolean remove(String name) {
        return passwords.remove(name) != null;
    }

    public String getPassword(String name) {
        return passwords.get(name);
    }

    public boolean edit(String name, String password) {
        return passwords.containsKey(name) && passwords.put(name, password) != null;
    }
}
