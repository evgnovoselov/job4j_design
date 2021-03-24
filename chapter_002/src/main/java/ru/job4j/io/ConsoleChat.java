package ru.job4j.io;

/**
 * Консольный чат с ботом.
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;

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
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}
