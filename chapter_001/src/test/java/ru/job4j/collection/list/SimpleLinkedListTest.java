package ru.job4j.collection.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Пакет для тестирования своего связного списка.
 *
 * @author Evgeny Novoselov
 */
public class SimpleLinkedListTest {
    @Test
    public void whenAddItemsThinHaveItems() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }
}
