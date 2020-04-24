package ru.job4j.generic;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тестирование универсального хранилища.
 *
 * @author Evgeny Novoselov
 */
public class MemStoreTest {
    /**
     * Тестируем добавление и получение элемента.
     */
    @Test
    public void whenAddElementAndGetThenHisGet() {
        Store<Base> store = new MemStore<>();
        User[] users = new User[]{
                new User("1"),
                new User("2"),
                new User("3"),
        };
        store.add(users[0]);
        store.add(users[1]);
        store.add(users[2]);
        assertThat(store.findById("2"), is(users[1]));
    }

    /**
     * Тестируем замену элемента.
     */
    @Test
    public void whenReplaceElementThenNewElement() {
        Store<Base> store = new MemStore<>();
        User[] users = new User[]{
                new User("1"),
                new User("2"),
                new User("3"),
        };
        store.add(users[0]);
        store.add(users[1]);
        assertThat(store.replace("2", users[2]), is(true));
        assertThat(store.findById("3"), is(users[2]));
    }

    /**
     * Тестируем удаление элемента.
     */
    @Test
    public void whenDeleteElementThenMemNotHis() {
        Store<Base> store = new MemStore<>();
        User[] users = new User[]{
                new User("1"),
                new User("2"),
                new User("3"),
        };
        store.add(users[0]);
        store.add(users[1]);
        store.add(users[2]);
        assertThat(store.findById("2"), is(users[1]));
        assertThat(store.delete("2"), is(true));
        assertThat(store.findById("2"), Matchers.nullValue());
    }
}
