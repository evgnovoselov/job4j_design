package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] values;
    private int row = -1;
    private int col = -1;
    private boolean hasNextItem = false;

    public MatrixIt(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        int indexRow = row;
        int indexCol = col;
        while (!hasNextItem && values.length > 0 && indexRow < values.length) {
            if (indexRow == -1) {
                indexRow++;
            }
            if (indexCol >= values[indexRow].length - 1) {
                indexRow++;
                indexCol = -1;
                continue;
            }
            if (indexCol < values[indexRow].length - 1) {
                col = ++indexCol;
                row = indexRow;
                hasNextItem = true;
                break;
            }
        }
        return hasNextItem;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        hasNextItem = false;
        return values[row][col];
    }
}