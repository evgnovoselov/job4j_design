package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        int foodExpiryPercent = getExpiryPercentBy(food);
        if (foodExpiryPercent >= 75 && foodExpiryPercent < 100) {
            food.setDiscount(10);
            result = true;
        }
        if (!result && foodExpiryPercent >= 25 && foodExpiryPercent < 75) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean add(Food food) {
        return accept(food) && foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        return foods.remove(food);
    }

    @Override
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }
}
