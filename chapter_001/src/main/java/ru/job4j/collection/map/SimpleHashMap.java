package ru.job4j.collection.map;

/**
 * Реализация простой хэш-карты.
 *
 * @author Evgeny Novoselov
 */
public class SimpleHashMap<K, V> {
    /**
     * Добавляем элемент в карту.
     *
     * @param key   Ключ
     * @param value Значение
     * @return Результат добавления элемента.
     */
    public boolean insert(K key, V value) {
        return false;
    }

    /**
     * Возвращаем значение по ключу.
     *
     * @param key Ключ.
     * @return Значение по ключу.
     */
    public V get(K key) {
        return null;
    }

    /**
     * Удаляем значение.
     *
     * @param key Ключ.
     * @return Результат удаления.
     */
    public boolean delete(K key) {
        return false;
    }
}
