package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    /**
     * Проверка генерации шаблона.
     */
    @Ignore
    @Test
    public void whenProduceTemplateWithKeysThenGetGeneratedTemplate() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Petr Arsentev", "subject", "you");
        Generator generator = new StringGenerator();
        String gen = generator.produce(template, keys);
        assertEquals("I am a Petr Arsentev, Who are you?", gen);
    }

    /**
     * Проверка на ошибку при генерации шаблона в котором есть ключи, которых нет в карте.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenLostKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Petr Arsentev");
        Generator generator = new StringGenerator();
        String gen = generator.produce(template, keys);
    }

    /**
     * Проверка на ошибку генерации шаблона при котором в карте есть лишние ключи.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapsHaveNoNeedKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Petr Arsentev", "subject", "you", "key3", "val3");
        Generator generator = new StringGenerator();
        String gen = generator.produce(template, keys);
    }
}