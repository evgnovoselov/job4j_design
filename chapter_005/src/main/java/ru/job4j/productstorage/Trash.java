package ru.job4j.productstorage;

import java.util.List;

/**
 * Данный класс удаляет продукты.
 */
public class Trash implements Storage {
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
