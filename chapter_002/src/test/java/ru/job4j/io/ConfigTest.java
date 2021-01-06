package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Класс для теста конфигурации.
 */
public class ConfigTest {
    /**
     * Проверка получения значения по ключу без комментариев.
     */
    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("../files/test/pair_without_comment.properties");
        config.load();
        assertThat(
                config.value("name"),
                is("Evgeny Novoselov")
        );
        assertThat(
                config.value("email"),
                is("kekeke@xxx.ru")
        );
    }

    /**
     * Получение значения ключа с пробелами, которые исключаются.
     */
    @Test
    public void whenPairWithKeySpaceThenWithoutKeySpace() {
        Config config = new Config("../files/test/pair_different_version.properties");
        config.load();
        assertThat(
                config.value("name"),
                is("Evgeny Novoselov")
        );
    }

    /**
     * Получение значения ключа, который содержит пробелы.
     */
    @Test
    public void whenPairWithValueSpaceThenWithoutValueSpace() {
        Config config = new Config("../files/test/pair_different_version.properties");
        config.load();
        assertThat(
                config.value("email"),
                is("kekeke@xxx.ru")
        );
    }

    /**
     * Получение настройки с ключом и значением содержащим пробелы.
     */
    @Test
    public void whenPairWithSpaceKeyAndValueThenWithoutSpace() {
        Config config = new Config("../files/test/pair_different_version.properties");
        config.load();
        assertThat(
                config.value("two_space"),
                is("value")
        );
    }

    /**
     * Проверка отсутствия ключа который содержит пробелы в значении.
     */
    @Test
    public void whenPairEmptyValueThenWithoutKeyAndValue() {
        Config config = new Config("../files/test/pair_different_version.properties");
        config.load();
        assertThat(
                config.value("space_value"),
                is(nullValue())
        );
    }

    /**
     * Проверка отсутствия значения у ключа который содержит пробелы.
     */
    @Test
    public void whenPairEmptyKeyThenWithoutKeyAndValue() {
        Config config = new Config("../files/test/pair_different_version.properties");
        config.load();
        assertThat(
                config.value(" "),
                is(nullValue())
        );
    }
}
