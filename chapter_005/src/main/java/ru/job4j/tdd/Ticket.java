package ru.job4j.tdd;

/**
 * Интерфейс билета.
 */
public interface Ticket {
    Cinema getCinema();

    Session getSession();

    int getRow();

    int getColumn();
}
