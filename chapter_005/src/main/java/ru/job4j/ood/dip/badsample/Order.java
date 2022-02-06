package ru.job4j.ood.dip.badsample;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Данный класс нарушает принцип SRP. Потому что предоставляет как саму модель заказа, так и методы для работы с ней.
 * Так же нарушает принцип DIP из-за того что использует базу в памяти, а не обстракцию. Хранилище заказа нужно
 * выделить в интерфейс и реализовать сервис заказа, зависящий от него.
 */
public class Order {
    private int id;
    private boolean isPayed;
    private Map<Integer, Product> products = new HashMap<>();

    public boolean add(Product product) {
        if (products.containsKey(product.getId())) {
            return false;
        }
        return products.put(product.getId(), product) != null;
    }

    public boolean remove(int id) {
        return products.remove(id) != null;
    }

    public void clear() {
        products.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
