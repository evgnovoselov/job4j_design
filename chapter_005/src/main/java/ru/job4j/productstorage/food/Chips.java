package ru.job4j.productstorage.food;

import java.time.LocalDate;

public class Chips extends Food {
    public Chips(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }

    public Chips(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
