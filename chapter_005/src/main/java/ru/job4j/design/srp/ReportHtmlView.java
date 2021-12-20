package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportHtmlView implements Report {
    private Store store;

    public ReportHtmlView(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}
