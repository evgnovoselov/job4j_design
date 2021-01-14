package ru.job4j.io;

import java.io.*;

/**
 * Класс анализа лога сервера.
 */
public class Analyze {
    /**
     * Метод проверяет файл лога доступности сервера в формате <code>type date</code>
     * и создает файл с отрезками времени недоступностью сервера в формате <code>time_down;time_up;</code>.
     *
     * @param source Путь к файлу лога сервера.
     * @param target Путь к файлу анализа лога.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String line;
            boolean unavailable = false;
            while ((line = in.readLine()) != null) {
                String time = line.substring(line.indexOf(" ") + 1) + ";";
                if (!unavailable) {
                    if (line.startsWith("400") || line.startsWith("500")) {
                        unavailable = true;
                        out.write(time);
                    }
                }
                if (unavailable) {
                    if (line.startsWith("200") || line.startsWith("300")) {
                        unavailable = false;
                        out.println(time);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
