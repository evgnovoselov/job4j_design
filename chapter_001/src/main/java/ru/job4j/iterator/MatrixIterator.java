package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;

    private int row = 0;
    private int col = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return (values.length > 0 && row < values.length - 1
                || values.length > 0 && row == values.length - 1 && col < values[row].length);
    }

    @Override
    public Integer next() {
        if (col >= values[row].length) {
            row++;
            col = 0;
        }
        return values[row][col++];
    }
}