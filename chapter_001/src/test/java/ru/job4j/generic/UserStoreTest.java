package ru.job4j.generic;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тест хранилища пользователей.
 *
 * @author Evgeny Novoselov
 */
public class UserStoreTest {
    /**
     * Тестируем добавление и получение пользователя.
     */
    @Test
    public void whenAddElementAndGetThenHisGet() {
        UserStore store = new UserStore();
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
     * Тестируем замену пользователя.
     */
    @Test
    public void whenReplaceElementThenNewElement() {
        UserStore store = new UserStore();
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
     * Тестируем удаление пользователя.
     */
    @Test
    public void whenDeleteElementThenMemNotHis() {
        UserStore store = new UserStore();
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
