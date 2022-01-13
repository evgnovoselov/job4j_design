package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class ControlQuality {
    private List<Rule> rules = new ArrayList<>();

    public void addRule(String name, Storage storage, Predicate<Food> foodRule, int discount) {
        Rule rule = new Rule(name, storage, foodRule, discount);
        rules.add(rule);
    }

    public Map<Integer, String> getRules() {
        Map<Integer, String> result = new LinkedHashMap<>();
        for (int i = 0; i < rules.size(); i++) {
            result.put(i, rules.get(i).name);
        }
        return result;
    }

    public boolean removeRule(int index) {
        return rules.remove(index) != null;
    }

    public void distributeFoods(List<Food> foods) {
        for (Food food : foods) {
            for (Rule rule : rules) {
                if (rule.foodRule.test(food)) {
                    food.setDiscount(rule.discount);
                    rule.storage.add(food);
                }
            }
        }
    }

    private static class Rule {
        private String name;
        private Storage storage;
        private Predicate<Food> foodRule;
        private int discount;

        public Rule(String name, Storage storage, Predicate<Food> foodRule, int discount) {
            this.name = name;
            this.storage = storage;
            this.foodRule = foodRule;
            this.discount = discount;
        }
    }
}
