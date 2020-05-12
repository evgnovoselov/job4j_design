package ru.job4j.collection;

/**
 * Простой стек.
 *
 * @author Evgeny Novoselov
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}
