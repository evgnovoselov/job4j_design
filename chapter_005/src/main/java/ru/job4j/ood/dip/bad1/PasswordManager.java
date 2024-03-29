package ru.job4j.ood.dip.bad1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;

/**
 * Данный класс нарушает принцип инверсии зависимости (DIP), тем что он привязан к реализации хранилища в памяти.
 * В данном классе нужно использовать абстракцию хранилища. Так же наблюдается нарушение принципа SRP в кодировщике
 * пароля, методы кодировки и раскодировке можно было выделить в отдельный интерфейс и создать сервис по кодировке.
 */
public class PasswordManager {
    private final MemoryPasswordStorage storage = new MemoryPasswordStorage();
    private final String salt = "IEasySalt";

    public boolean add(String name, String password) {
        return storage.add(name, encrypt(password));
    }

    public Set<String> getNames() {
        return storage.getNames();
    }

    public boolean remove(String name) {
        return storage.remove(name);
    }

    public String getPassword(String name) {
        return decrypt(storage.getPassword(name));
    }

    public boolean edit(String name, String password) {
        return storage.edit(name, encrypt(password));
    }

    private String encrypt(String password) {
        return Base64.getEncoder()
                .encodeToString(String.format("%s%s", salt, password).getBytes(StandardCharsets.UTF_8));
    }

    private String decrypt(String password) {
        return new String(Base64.getDecoder().decode(password)).substring(salt.length());
    }
}
