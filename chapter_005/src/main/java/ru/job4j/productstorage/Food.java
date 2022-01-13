package ru.job4j.productstorage;

import java.time.LocalDate;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getExpiryPercentOfDate(LocalDate date) {
        long lifeDays = expiryDate.toEpochDay() - createDate.toEpochDay();
        long passedDays = lifeDays - (expiryDate.toEpochDay() - date.toEpochDay());
        return (int) (passedDays * 100 / lifeDays);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
