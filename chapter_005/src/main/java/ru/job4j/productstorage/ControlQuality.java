package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ControlQuality {
    private Map<Storage, Predicate<Integer>> storageRules;

    public ControlQuality(Map<Storage, Predicate<Integer>> storageRules) {
        this.storageRules = storageRules;
    }

    public void distributeFoods(LocalDate date, List<Food> foods) {
        for (Food food : foods) {
            for (Map.Entry<Storage, Predicate<Integer>> entry : storageRules.entrySet()) {
                if (entry.getValue().test(food.getExpiryPercentOfDate(date))) {
                    entry.getKey().add(food);
                }
            }
        }
    }
}
