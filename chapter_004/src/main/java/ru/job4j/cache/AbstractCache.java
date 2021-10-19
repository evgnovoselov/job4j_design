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
        V result = null;
        SoftReference<V> reference = cache.get(key);
        if (reference == null || reference.get() == null) {
            result = load(key);
        }
        return result;
    }

    protected abstract V load(K key);
}
