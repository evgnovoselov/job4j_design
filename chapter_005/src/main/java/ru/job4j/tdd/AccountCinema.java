package ru.job4j.tdd;

import java.util.List;
import java.util.function.Predicate;

public class AccountCinema implements Account {
    @Override
    public List<Ticket> findTickets(Predicate<Ticket> predicate) {
        return List.of(new Ticket3D());
    }
}
