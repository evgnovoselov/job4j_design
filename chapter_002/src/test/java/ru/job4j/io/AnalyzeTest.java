package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Класс для проверки метода анализа лога доступности сервера.
 */
public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Метод проверяет отрезки недоступности сервера.
     */
    @Test
    public void whenHaveDownTimeThenTime() throws IOException {
        File source = folder.newFile("server_down_time.log");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = folder.newFile("unavailable.csv");
        Analyze analyze = new Analyze();
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
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
    public void whenServerUpDownAndNotUpThenNotHaveUpTime() throws IOException {
        File source = folder.newFile("server_up_down_and_not_up.log");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 11:01:02");
            out.println("400 11:02:02");
        }
        File target = folder.newFile("unavailable.csv");
        Analyze analyze = new Analyze();
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
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
    public void whenDownThenHaveDownTime() throws IOException {
        File source = folder.newFile("server_down.log");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 11:01:02");
            out.println("400 11:02:02");
        }
        File target = folder.newFile("unavailable.csv");
        Analyze analyze = new Analyze();
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
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
    public void whenDownUpThenHaveTime() throws IOException {
        File source = folder.newFile("server_down_up.log");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 11:01:02");
            out.println("300 11:02:02");
        }
        File target = folder.newFile("unavailable.csv");
        Analyze analyze = new Analyze();
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expected = null;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            expected = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(List.of("10:57:01;11:02:02;")));
    }
}
