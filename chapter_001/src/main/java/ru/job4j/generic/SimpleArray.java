package ru.job4j.generic;

/**
 * Обертка над массивом с использованием generic
 *
 * @author Evgeny Novoselov
 */
public class SimpleArray<T> {
    private Object[] elements;
    private int position = 0;

    public SimpleArray(int size) {
        this.elements = new Object[size];
    }

    /**
     * Добавляет элемент.
     *
     * @param model элемент.
     */
    public void add(T model) {
        if (position >= elements.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[position++] = model;
    }

    /**
     * Заменяем элемент по индексу.
     *
     * @param index индекс.
     * @param model элемент.
     */
    public void set(int index, T model) {
        elements[index] = model;
    }

    /**
     * Удаляет элемент и сдвигает остальные элементы, уберая промежуток.
     *
     * @param index индекс.
     */
    public void remove(int index) {
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        elements[--position] = null;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index индекс.
     * @return элемент.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) elements[index];
    }
}
