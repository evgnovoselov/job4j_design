package ru.job4j.productstorage.food;

import java.time.LocalDate;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this(name, createDate, expiryDate, price, 0);
    }

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        if (expiryDate.toEpochDay() - createDate.toEpochDay() <= 0) {
            throw new IllegalArgumentException("Ошибка в указании дат.");
        }
        validatePrice(price);
        validateDiscount(discount);
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
        if (expiryDate.toEpochDay() - createDate.toEpochDay() <= 0) {
            throw new IllegalArgumentException("Ошибка в указании даты изготовления");
        }
        this.createDate = createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate.toEpochDay() - createDate.toEpochDay() <= 0) {
            throw new IllegalArgumentException("Ошибка в указании даты годности.");
        }
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        validateDiscount(discount);
        this.discount = discount;
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть меньше 0.");
        }
    }

    private void validateDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Неверная скидка.");
        }
    }

    public int getExpiryPercentOfDate(LocalDate date) {
        long lifeDays = expiryDate.toEpochDay() - createDate.toEpochDay();
        long passedDays = lifeDays - (expiryDate.toEpochDay() - date.toEpochDay());
        return (int) (passedDays * 100 / lifeDays);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
