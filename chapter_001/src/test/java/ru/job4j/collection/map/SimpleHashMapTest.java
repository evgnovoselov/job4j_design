package ru.job4j.collection.map;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тестируем реализацию простой хэш-карты.
 *
 * @author Evgeny Novoselov
 */
public class SimpleHashMapTest {
    /**
     * Тестируем добавление элементов.
     */
    @Test
    public void whenTryInsertThenNotProblem() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        assertThat(shm.insert("one", 1), is(true));
        assertThat(shm.insert("two", 2), is(true));
        assertThat(shm.insert("one", 3), is(false));
    }

    /**
     * Тестируем получение элементов.
     */
    @Test
    public void whenInsertElemThenGetHis() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("one", 1);
        shm.insert("two", 2);
        shm.insert("one", 3);
        assertThat(shm.get("one"), is(1));
        assertThat(shm.get("two"), is(2));
    }

    /**
     * Тестируем удаление элемента.
     */
    @Test
    public void whenDeleteElemThenGetOther() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("one", 1);
        shm.insert("two", 2);
        assertThat(shm.delete("one"), is(true));
        assertThat(shm.delete("three"), is(false));
        shm.insert("one", 3);
        assertThat(shm.get("one"), is(3));
        assertThat(shm.get("two"), is(2));
    }
}
