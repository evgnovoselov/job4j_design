package ru.job4j.collection;

/**
 * Реализация простой очереди.
 *
 * @param <T> тип.
 * @author Evgeny Novoselov
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        return null;
    }

    public void push(T value) {
    }
}
