package ru.job4j.io;

/**
 * Класс для получение таблицы умножения.
 */
public class Matrix {
    public static int[][] multiple(int row, int col) {
        int[][] multiple = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                multiple[i][j] = (i + 1) * (j + 1);
            }
        }
        return multiple;
    }
}
