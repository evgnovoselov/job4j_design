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

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return lookForNextEven() != -1;
    }

    @Override
    public Integer next() {
        int nextIndex = lookForNextEven();
        if (nextIndex != -1) {
            index = nextIndex;
        } else {
            throw new NoSuchElementException();
        }
        return values[index];
    }

    private int lookForNextEven() {
        int result = -1;
        for (int i = index + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
