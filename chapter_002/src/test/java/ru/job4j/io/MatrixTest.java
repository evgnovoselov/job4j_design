package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тестируем класс матрицы.
 */
public class MatrixTest {
    /**
     * Тестируем получение таблицы умножения.
     */
    @Test
    public void when4On4() {
        int[][] multiple = Matrix.multiple(4, 4);
        int[][] expected = {
                {1, 2, 3, 4},
                {2, 4, 6, 8},
                {3, 6, 9, 12},
                {4, 8, 12, 16}
        };
        assertThat(multiple, is(expected));
    }
}
