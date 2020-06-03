package ru.job4j.collection.map;

import java.util.Iterator;

/**
 * Реализация простой хэш-карты.
 *
 * @author Evgeny Novoselov
 */
public class SimpleHashMap<K, V> implements Iterable<V> {
    @SuppressWarnings("unchecked")
    private Node<K, V>[] table = (Node<K, V>[]) new Node[16];

    /**
     * Добавляем элемент в карту.
     *
     * @param key   Ключ
     * @param value Значение
     * @return Результат добавления элемента.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int position = getPosition(key);
        if (table[position] == null) {
            table[position] = new Node<>(key, value);
            result = true;
        }
        return result;
    }

    private int getPosition(K key) {
        return key.hashCode() % table.length;
    }

    /**
     * Возвращаем значение по ключу.
     *
     * @param key Ключ.
     * @return Значение по ключу.
     */
    public V get(K key) {
        int position = getPosition(key);
        return table[position] != null ? table[position].value : null;
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

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public V next() {
                return null;
            }
        };
    }

    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
