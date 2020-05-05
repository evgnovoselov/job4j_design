package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findNext();
    }

    private boolean findNext() {
        int indexRow = row;
        int indexColumn = column;
        boolean result = false;
        while (indexRow < data.length) {
            if (data[indexRow].length > 0 && indexColumn < data[indexRow].length) {
                result = true;
                break;
            }
            indexRow++;
            indexColumn = 0;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (column >= data[row].length) {
            row++;
            column = 0;
        }
        return data[row][column++];
    }
}