package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int row = -1;
    private int col = -1;
    private int index = 0;
    private int nextRow = -1;
    private int nextCol = -1;
    private int nextIndex = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        int indexRow = nextRow;
        int indexCol = nextCol;
        while (index >= nextIndex && values.length > 0 && indexRow < values.length) {
            if (indexRow == -1) {
                indexRow++;
            }
            if (indexCol >= values[indexRow].length - 1) {
                indexRow++;
                indexCol = -1;
                continue;
            }
            if (indexCol < values[indexRow].length - 1) {
                nextCol = ++indexCol;
                nextRow = indexRow;
                nextIndex++;
                break;
            }
        }
        return index < nextIndex;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        row = nextRow;
        col = nextCol;
        index = nextIndex;
        return values[row][col];
    }
}