package ru.job4j.tdd;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс пользователя.
 */
public interface Account {
    List<Ticket> findTickets(Predicate<Ticket> predicate);
}
