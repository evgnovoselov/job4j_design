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

    public void addRule(String name, Storage storage, Predicate<Integer> percent, int discount) {
        Rule rule = new Rule(name, storage, percent, discount);
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

    public void distributeFoods(LocalDate date, List<Food> foods) {
        for (Food food : foods) {
            for (Rule rule : rules) {
                if (rule.percentRule.test(food.getExpiryPercentOfDate(date))) {
                    food.setDiscount(rule.discount);
                    rule.storage.add(food);
                }
            }
        }
    }

    private static class Rule {
        private String name;
        private Storage storage;
        private Predicate<Integer> percentRule;
        private int discount;

        public Rule(String name, Storage storage, Predicate<Integer> percentRule, int discount) {
            this.name = name;
            this.storage = storage;
            this.percentRule = percentRule;
            this.discount = discount;
        }
    }
}
