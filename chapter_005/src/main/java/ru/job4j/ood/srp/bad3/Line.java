package ru.job4j.ood.srp.bad3;

public class Line {
    private int start;
    private int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Line{"
                + "start=" + start
                + ", end=" + end
                + '}';
    }
}
