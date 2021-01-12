package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Класс для проверки метода анализа лога доступности сервера.
 */
public class AnalyzeTest {
    private final String target = "../files/test/unavailable.csv";

    /**
     * Метод проверяет отрезки недоступности сервера.
     */
    @Test
    public void whenHaveDownTimeThenTime() {
        Analyze analyze = new Analyze();
        String source = "../files/test/server_down_time.log";
        analyze.unavailable(source, target);
        List<String> expected = null;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            expected = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;")));
    }

    /**
     * Метод проверяет лог, когда сервер перестал быть доступен совсем.
     */
    @Test
    public void whenServerUpDownAndNotUpThenNotHaveUpTime() {
        Analyze analyze = new Analyze();
        String source = "../files/test/server_up_down_and_not_up.log";
        analyze.unavailable(source, target);
        List<String> expected = null;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            expected = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(List.of("10:57:01;")));
    }

    /**
     * Метод проверяет лог, когда сервер не был изначально доступен и до конца лога.
     */
    @Test
    public void whenDownThenHaveDownTime() {
        Analyze analyze = new Analyze();
        String source = "../files/test/server_down.log";
        analyze.unavailable(source, target);
        List<String> expected = null;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            expected = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(List.of("10:57:01;")));
    }

    /**
     * Метод проверяет лог, когда сервер был не доступен и заработал.
     */
    @Test
    public void whenDownUpThenHaveTime() {
        Analyze analyze = new Analyze();
        String source = "../files/test/server_down_up.log";
        analyze.unavailable(source, target);
        List<String> expected = null;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            expected = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(List.of("10:57:01;11:02:02;")));
    }
}
