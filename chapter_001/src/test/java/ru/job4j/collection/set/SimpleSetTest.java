package ru.job4j.collection.set;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тестирование простого множества.
 */
public class SimpleSetTest {
    @Test
    public void whenAddElementsThenHaveHis() {
        SimpleSet<Integer> data = new SimpleSet<>();
        data.add(1);
        data.add(2);
        data.add(2);
        data.add(3);
        data.add(3);
        var it = data.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }
}
