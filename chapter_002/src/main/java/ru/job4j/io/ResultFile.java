package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Класс для записи результата получения таблицы умножения в файл.
 */
public class ResultFile {
    public static void main(String[] args) {
        int[][] multiple = Matrix.multiple(9, 9);
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : multiple) {
            for (int val : row) {
                stringBuilder.append(val).append(" ");
            }
            stringBuilder.append("\n");
        }
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(stringBuilder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
