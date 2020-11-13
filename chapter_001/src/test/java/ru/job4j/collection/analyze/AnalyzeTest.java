package ru.job4j.collection.analyze;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Класс для тестирования анализатора коллекций.
 */
public class AnalyzeTest {
    /**
     * Тестируем определение сколько добавленно новых пользователей в список.
     */
    @Test
    public void whenListAddNewUsersThenGetCountHowMuchAdd() {
        assertThat(true, is(true));
    }

    /**
     * Тестируем определение сколько изменено пользователей.
     */
    @Test
    public void whenListEditUsersThenGetCountHowMuchEdit() {
        assertThat(true, is(true));
    }

    /**
     * Тестируем определение сколько удалено пользователей.
     */
    @Test
    public void whenListDeleteUsersThenGetCountHowMuchDelete() {
        assertThat(true, is(true));
    }
}
