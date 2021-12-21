package ru.job4j.design.srp;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Генерация отчета с изменением вида зарплаты.
 */
public class ReportEngineChangeSalary implements Report {
    private final Store store;
    private final Function<Double, String> changedSalary;

    public ReportEngineChangeSalary(Store store, Function<Double, String> changedSalary) {
        this.store = store;
        this.changedSalary = changedSalary;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(changedSalary.apply(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
