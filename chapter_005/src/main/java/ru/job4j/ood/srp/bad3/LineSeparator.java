package ru.job4j.ood.srp.bad3;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Неправильный пример SRP.
 * Класс создает объекты прямых и выводит их.
 */
public class LineSeparator {
    private List<Line> lines = new LinkedList<>();

    public LineSeparator(int start, int end) {
        lines.add(new Line(start, end));
    }

    public void split(int point) {
        ListIterator<Line> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            Line current = iterator.next();
            if (point > current.getStart() && point < current.getEnd()) {
                int curOldEnd = current.getEnd();
                if (point == curOldEnd - 1) {
                    current.setEnd(point - 1);
                    iterator.add(new Line(point, curOldEnd));
                } else {
                    current.setEnd(point);
                    iterator.add(new Line(point + 1, curOldEnd));
                }
                break;
            }
        }
    }

    public void printToConsole() {
        System.out.println(lines);
    }

    public static void main(String[] args) {
        LineSeparator lineSeparator = new LineSeparator(1, 10);
        lineSeparator.printToConsole();
        lineSeparator.split(3);
        lineSeparator.printToConsole();
        lineSeparator.split(5);
        lineSeparator.printToConsole();
        lineSeparator.split(9);
        lineSeparator.printToConsole();
    }
}
