package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    List<Food> foods = new ArrayList<>();

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
