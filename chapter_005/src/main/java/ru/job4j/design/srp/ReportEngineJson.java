package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Генерация отчета в формате Json.
 */
public class ReportEngineJson implements Report {
    private Store store;

    public ReportEngineJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return new GsonBuilder().create().toJson(employees);
    }
}
