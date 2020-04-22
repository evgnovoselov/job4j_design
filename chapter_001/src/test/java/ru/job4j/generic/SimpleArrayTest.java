package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Класс для тестирования обертки над массивом с использованием generic.
 *
 * @author Evgeny Novoselov
 */
public class SimpleArrayTest {
    /**
     * Проверка добавления элементов.
     */
    @Test
    public void whenAddElementsThenGetsHis() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        assertThat(sa.get(0), is(1));
        assertThat(sa.get(1), is(2));
        assertThat(sa.get(2), is(3));
    }

    /**
     * Проверка на ошибку при добавлении больше элементов, чем размер массива.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddManyItemsThenErrors() {
        SimpleArray<Integer> sa = new SimpleArray<>(2);
        sa.add(1);
        sa.add(2);
        sa.add(3);
    }

    /**
     * Проверка замены элемента по индексу.
     */
    @Test
    public void whenSetElementFromIndexThenEditElement() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        sa.set(1, 4);
        assertThat(sa.get(0), is(1));
        assertThat(sa.get(1), is(4));
        assertThat(sa.get(2), is(3));
    }

    /**
     * Проверка метода удаления элемента.
     */
    @Test
    public void whenDeleteElementThenNotHaveHis() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        sa.remove(1);
        assertThat(sa.get(0), is(1));
        assertThat(sa.get(1), is(3));
        sa.add(4);
        assertThat(sa.get(2), is(4));
    }

    /**
     * Тестирование работы итератора.
     */
    @Test
    public void whenGetIteratorThenHaveIterator() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        Iterator<Integer> it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        it.remove();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        it.remove();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тестирование ошибки итератора при двойном удалении подряд.
     */
    @Test(expected = IllegalStateException.class)
    public void whenIteratorRemoveTwoElementThenError() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        Iterator<Integer> it = sa.iterator();
        it.next();
        it.next();
        it.remove();
        it.remove();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenNotHaveIndexElementThenError() {
        SimpleArray<Integer> sa = new SimpleArray<>(1);
        sa.add(1);
        sa.remove(0);
        sa.set(0, 1);
    }
}
