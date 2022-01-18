package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.util.List;

public interface Storage {
    void add(Food food);

    boolean remove(Food food);

    List<Food> getFoods();
}
