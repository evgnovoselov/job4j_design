package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

/**
 * Простое множество.
 */
public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> data = new SimpleArray<>();

    void add(E e) {
        if (!contains(e)) {
            data.add(e);
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    private boolean contains(E e) {
        boolean isContains = false;
        for (E value : data) {
            if (e.equals(value)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }
}
