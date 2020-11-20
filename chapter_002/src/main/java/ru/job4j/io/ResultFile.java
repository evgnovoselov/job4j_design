package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Класс для записи результата получения таблицы умножения в файл.
 */
public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(getMultiple(9).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMultiple(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : Matrix.multiple(size, size)) {
            for (int val : row) {
                stringBuilder.append(val).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
