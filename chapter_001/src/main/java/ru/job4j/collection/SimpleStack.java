package ru.job4j.collection;

/**
 * Простой стек.
 *
 * @author Evgeny Novoselov
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T elem = null;
        for (T value : linked) {
            elem = value;
        }
        linked.deleteLast();
        return elem;
    }

    public void push(T value) {
        linked.add(value);
    }
}
