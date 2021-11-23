package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private int id = 1;

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return List.of(new Session3D());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cinema3D cinema3D = (Cinema3D) o;
        return id == cinema3D.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
