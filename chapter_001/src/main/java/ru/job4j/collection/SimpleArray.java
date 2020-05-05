package ru.job4j.collection;

import java.util.*;

/**
 * Хранилище на основе массива.
 *
 * @author Evgeny Novoselov
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int position = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) container[Objects.checkIndex(index, position)];
    }

    public void add(T model) {
        if (position + 1 >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[position++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < position;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[index++];
            }
        };
    }
}
