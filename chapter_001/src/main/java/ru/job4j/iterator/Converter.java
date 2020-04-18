package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO add comments
 *
 * @author Evgeny Novoselov
 */
public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> includeIt;

            @Override
            public boolean hasNext() {
                if ((includeIt == null || !includeIt.hasNext()) && it.hasNext()) {
                    includeIt = it.next();
                }
                return includeIt != null && includeIt.hasNext();
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
