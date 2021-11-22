package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Класс тестирования получения максимального и минимального значения.
 */
public class MaxMinTest {
    /**
     * Проверяем получение максимального значения из списка.
     */
    @Test
    public void whenNeedMaxThenGetMax() {
        List<Integer> value = List.of(1, 2, 5, 4, 3);
        Integer expected = 5;
        assertEquals(expected, new MaxMin().max(value, Comparator.naturalOrder()));
    }

    /**
     * Проверяем получение пустой ссылки при пустом списке.
     */
    @Test
    public void whenEmptyListThenGetNull() {
        List<Integer> value = List.of();
        assertNull(new MaxMin().max(value, Comparator.naturalOrder()));
    }

    /**
     * Проверяем получение элемента при передачи списка с одним значением.
     */
    @Test
    public void whenListHaveOneValueThenGetValue() {
        List<Integer> value = List.of(5);
        Integer expected = 5;
        assertEquals(expected, new MaxMin().max(value, Comparator.naturalOrder()));
    }

    /**
     * Проверяем работу получения минимального значения из списка.
     */
    @Test
    public void whenNeedMinThenGetMin() {
        List<Integer> value = List.of(1, 2, 5, 4, 3);
        Integer expected = 1;
        assertEquals(expected, new MaxMin().min(value, Comparator.naturalOrder()));
    }

    /**
     * Проверяем работу метода по получению большего значения по предикату.
     */
    @Test
    public void whenCompareByBiPredicateThenGetValue() {
        List<Integer> value = List.of(1, 2, 3, 5, 4, 3, 2);
        Integer expected = 5;
        assertEquals(expected, new MaxMin().compareBy(value, (integer, integer2) -> integer > integer2));
    }
}