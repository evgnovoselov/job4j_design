package ru.job4j.productstorage.food;

import java.time.LocalDate;

public class Fruit extends Food {
    public Fruit(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }

    public Fruit(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
