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
    public Food remove(Food food) {
        Food res = null;
        return null;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }
}
