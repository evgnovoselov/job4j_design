package ru.job4j.tdd;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование системы кинотиатра.
 */
public class CinemaTest {
    /**
     * Поиск сеансов по предикату в кинотеатре.
     */
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertEquals(sessions, List.of(new Session3D()));
    }

    /**
     * Покупка билета в кинотеатре.
     */
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.NOVEMBER, 22, 13, 11);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertEquals(ticket, new Ticket3D());
    }

    /**
     * Получение билета пользователя по предикату.
     */
    @Test
    public void whenGetAccountTicketsThenTickets() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.NOVEMBER, 22, 13, 11);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        List<Ticket> tickets = account.findTickets(t -> true);
        assertEquals(tickets, List.of(new Ticket3D()));
    }

    /**
     * Получение кинотеатра побилету.
     */
    @Test
    public void whenGetCinemaFromTicketThenCinema() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.NOVEMBER, 22, 13, 11);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertEquals(cinema, ticket.getCinema());
    }

    /**
     * Получение сеанса побилету.
     */
    @Test
    public void whenGetSessionFromTicketThenSession() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.NOVEMBER, 22, 13, 11);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Session session = cinema.find(s -> true).get(0);
        assertEquals(session, ticket.getSession());
    }

    /**
     * Получение места по билету.
     */
    @Test
    public void whenGetRowAndColFromTicketThenRowCol() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.NOVEMBER, 22, 13, 11);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertEquals(1, ticket.getRow());
        assertEquals(1, ticket.getColumn());
    }
}