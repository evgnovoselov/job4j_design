package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация итератора по четным числам.
 *
 * @author Evgeny Novoselov
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] values;
    private int index = -1;
    private int nextIndex = -1;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < nextIndex || index != changeNextIndexEven();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = nextIndex;
        return values[index];
    }

    private int changeNextIndexEven() {
        for (int i = index + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                nextIndex = i;
                break;
            }
        }
        return nextIndex;
    }
}
