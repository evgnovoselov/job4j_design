package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Конвертер.
 *
 * @author Evgeny Novoselov
 */
public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> includeIt = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                if (!includeIt.hasNext() && it.hasNext()) {
                    includeIt = it.next();
                }
                return includeIt.hasNext();
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return includeIt.next();
            }
        };
    }
}
