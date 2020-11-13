package ru.job4j.collection.analyze;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        var previous = List.of(
                new Analyze.User("Veronika"),
                new Analyze.User("Alex"),
                new Analyze.User("Julia"),
                new Analyze.User("Katya")
        );
        var current = new ArrayList<>(previous);
        current.addAll(List.of(
                new Analyze.User("Olga"),
                new Analyze.User("Kristina")
        ));
        assertThat(Analyze.diff(previous, current), is(2));
    }

    /**
     * Тестируем определение сколько изменено пользователей.
     */
    @Test
    public void whenListEditUsersThenGetCountHowMuchEdit() {
        var previous = List.of(
                new Analyze.User("Veronika"),
                new Analyze.User("Alex"),
                new Analyze.User("Julia"),
                new Analyze.User("Katya")
        );
        var current = new ArrayList<>(previous);
        current.get(1).setName("Olga");
        current.get(2).setName("Kristina");
        assertThat(Analyze.diff(previous, current), is(2));
    }

    /**
     * Тестируем определение сколько удалено пользователей.
     */
    @Test
    public void whenListDeleteUsersThenGetCountHowMuchDelete() {
        var previous = List.of(
                new Analyze.User("Veronika"),
                new Analyze.User("Alex"),
                new Analyze.User("Julia"),
                new Analyze.User("Katya")
        );
        var current = new ArrayList<>(previous);
        current.remove(1);
        current.remove(2);
        assertThat(Analyze.diff(previous, current), is(2));
    }
}
