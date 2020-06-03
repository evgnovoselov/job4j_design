package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        assertThat(shm.delete("notHave"), is(false));
        shm.insert("one", 3);
        assertThat(shm.get("one"), is(3));
        assertThat(shm.get("two"), is(2));
    }

    /**
     * Тест итератора.
     */
    @Test
    public void whenGetIteratorThenItWorks() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("one", 1);
        shm.insert("two", 2);
        Iterator<Integer> it = shm.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест ошибки итератора при модификации коллекции.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenModificationMapThenErrorIterator() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("one", 1);
        Iterator<Integer> it = shm.iterator();
        shm.insert("two", 2);
        it.next();
    }

    /**
     * Тест ошибки получения элемента по итератору.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoSuchElemThenException() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<>();
        shm.insert("one", 1);
        shm.insert("two", 2);
        Iterator<Integer> it = shm.iterator();
        it.next();
        it.next();
        it.next();
    }

    /**
     * Тест изменения размера карты.
     */
    @Test
    public void whenInputMoreElementsThenResizeMap() {
        SimpleHashMap<Integer, Integer> shm = new SimpleHashMap<>();
        for (int i = 0; i < 32; i++) {
            shm.insert(i, i + 100);
        }
        assertThat(shm.get(1), is(101));
        assertThat(shm.get(4), is(104));
        assertThat(shm.get(16), is(116));
        assertThat(shm.get(25), is(125));
        assertThat(shm.get(31), is(131));
    }
}
