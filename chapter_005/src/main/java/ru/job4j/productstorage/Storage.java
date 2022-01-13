package ru.job4j.productstorage;

import java.util.List;

public interface Storage {
    void add(Food food);

    Food remove(Food food);

    List<Food> getFoods();
}
