package ru.job4j.collection.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Пакет для тестирования своего связного списка.
 *
 * @author Evgeny Novoselov
 */
public class SimpleLinkedListTest {
    /**
     * Тестируем получение элементов по индексу.
     */
    @Test
    public void whenAddItemsThenHaveItems() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    /**
     * Тестируем ошибку на неверный индекс.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetBadIndexThenError() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.get(7);
    }

    /**
     * Тестируем ошибку итератора при изменении списка.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenListChangeThenIteratorError() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        list.add(2);
        it.next();
    }

    /**
     * Тестируем получение элементов по итератору.
     */
    @Test
    public void whenGetItemsFromIteratorThenItems() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        var it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тестируем ошибку итератора при получение больше значений чем есть.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetMoreItemsFromIteratorThenError() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        var it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
