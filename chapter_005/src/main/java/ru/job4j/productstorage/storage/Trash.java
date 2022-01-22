package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.util.List;

/**
 * Данный класс удаляет продукты.
 */
public class Trash implements Storage {
    @Override
    public boolean accept(Food food) {
        return false;
    }

    @Override
    public void add(Food food) {
    }

    @Override
    public boolean remove(Food food) {
        return true;
    }

    @Override
    public List<Food> getFoods() {
        return List.of();
    }
}
