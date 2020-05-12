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
    private int size = 0;

    public T poll() {
        moveElements(in, out);
        T elem = out.pop();
        size--;
        moveElements(out, in);
        return elem;
    }

    public void push(T value) {
        size++;
        in.push(value);
    }

    private void moveElements(SimpleStack<T> from, SimpleStack<T> to) {
        for (int i = 0; i < size; i++) {
            to.push(from.pop());
        }
    }
}
