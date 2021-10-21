package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Базовый класс для работы с кешем.
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        SoftReference<V> reference = cache.getOrDefault(key, new SoftReference<>(load(key)));
        V result = reference.get();
        if (result == null) {
            result = load(key);
        }
        put(key, result);
        return result;
    }

    protected abstract V load(K key);
}
