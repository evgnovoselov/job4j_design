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

    public void addRule(String name, Storage storage, Predicate<Integer> percent, Function<Integer, Integer> changeDiscount) {
        Rule rule = new Rule(name, storage, percent, changeDiscount);
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
    }

    private static class Rule {
        private String name;
        private Storage storage;
        private Predicate<Integer> percentRule;
        private Function<Integer, Integer> changeDiscount;

        public Rule(String name, Storage storage, Predicate<Integer> percentRule, Function<Integer, Integer> changeDiscount) {
            this.name = name;
            this.storage = storage;
            this.percentRule = percentRule;
            this.changeDiscount = changeDiscount;
        }
    }
}
