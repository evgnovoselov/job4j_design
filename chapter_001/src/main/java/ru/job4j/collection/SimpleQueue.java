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
    private int sizeIn = 0;
    private int sizeOut = 0;

    public T poll() {
        if (sizeOut == 0) {
            for (int i = 0; i < sizeIn; i++) {
                out.push(in.pop());
            }
            sizeOut = sizeIn;
            sizeIn = 0;
        }
        T elem = out.pop();
        sizeOut--;
        return elem;
    }

    public void push(T value) {
        sizeIn++;
        in.push(value);
    }
}
