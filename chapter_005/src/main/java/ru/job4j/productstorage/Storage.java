package ru.job4j.productstorage;

import java.util.List;

public interface Storage {
    void add(Food food);

    boolean remove(Food food);

    List<Food> getFoods();
}
