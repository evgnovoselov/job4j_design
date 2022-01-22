package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return false;
    }

    @Override
    public boolean add(Food food) {
        return foods.add(food);
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
