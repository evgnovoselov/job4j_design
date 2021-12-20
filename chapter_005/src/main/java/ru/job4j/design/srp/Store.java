package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс базы данных.
 */
public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}
