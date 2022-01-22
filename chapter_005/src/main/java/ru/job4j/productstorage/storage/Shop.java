package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        return foods.remove(food);
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }
}
