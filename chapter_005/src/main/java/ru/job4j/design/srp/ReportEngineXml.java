package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Генерация отчета в формате XML.
 */
public class ReportEngineXml implements Report {
    private Store store;

    public ReportEngineXml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}
