package ru.job4j.generic;

import org.junit.Test;

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
        sa.add(4);
        assertThat(sa.get(0), is(1));
        assertThat(sa.get(1), is(3));
        assertThat(sa.get(2), is(4));
    }
}
