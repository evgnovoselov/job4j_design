package ru.job4j.ood.srp.bad2;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Неправильный пример SRP.
 * Класс рабоатет с представлением даты и выводит данные.
 */
public class Report {
    private Calendar calendar = new GregorianCalendar();
    private String content;

    public Report(String content) {
        this.content = content;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void toPrint() {
        System.out.printf("%s : %s%n", calendar.getTime(), content);
    }

    public static void main(String[] args) {
        Report report = new Report("Create report");
        report.toPrint();
    }
}
