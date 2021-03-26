package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Консольный чат с ботом.
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    /**
     * Конструктор консольного чата с ботом.
     *
     * @param path       Путь файла, в который будет записан весь диалог между ботом и пользователем.
     * @param botAnswers Путь файла, в котором находятся строки с ответами, которые будет использовать бот.
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод запуска консольного бота.
     */
    private void run() {
        String word;
        Set<String> statuses = Set.of(CONTINUE, STOP, OUT);
        String status = CONTINUE;
        System.out.println("ConsoleChat");
        List<String> answers = getBotAnswers();
        List<String> chatLog = new ArrayList<>();
        Random random = new Random();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                word = reader.readLine();
                chatLog.add(word);
                if (statuses.contains(word)) {
                    status = word;
                }
                if (status.equals(CONTINUE)) {
                    String answer = answers.get(random.nextInt(answers.size()));
                    System.out.println(answer);
                    chatLog.add(answer);
                }
            } while (!status.equals(OUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeChatLog(chatLog);
    }

    /**
     * Метод возвращает список ответов бота из заданного набора фраз.
     *
     * @return Список ответов бота.
     */
    private List<String> getBotAnswers() {
        List<String> answers = List.of("");
        try {
            answers = Files.readAllLines(Paths.get(botAnswers));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !answers.isEmpty() ? answers : List.of("");
    }

    /**
     * Сохраняем историю чата в файл.
     *
     * @param chatLog Список сообщений чата.
     */
    private void writeChatLog(List<String> chatLog) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), Charset.forName("WINDOWS-1251"))) {
            for (String line : chatLog) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./files/ignore/consoleChatLog.txt",
                "./files/ignore/botAnswers.txt"
        );
        cc.run();
    }
}
