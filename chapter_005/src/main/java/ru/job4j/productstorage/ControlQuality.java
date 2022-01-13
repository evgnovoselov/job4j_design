package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Контроллер правил распределения продуктов.
 */
public class ControlQuality {
    private List<Rule> rules = new ArrayList<>();

    /**
     * Добавляет правило.
     *
     * @param name     Имя правила.
     * @param storage  Хранилище для внесения.
     * @param foodRule Правило фильтрации.
     * @param discount Изменение скидки продукта.
     */
    public void addRule(String name, Storage storage, Predicate<Food> foodRule, int discount) {
        Rule rule = new Rule(name, storage, foodRule, discount);
        rules.add(rule);
    }

    /**
     * Удаляет правило распределения.
     *
     * @param index Индекс правила.
     * @return Успех удаления.
     */
    public boolean removeRule(int index) {
        return rules.remove(index) != null;
    }

    /**
     * Получение представления правил.
     *
     * @return Карта с индексами и именами правил.
     */
    public Map<Integer, String> getRules() {
        Map<Integer, String> result = new LinkedHashMap<>();
        for (int i = 0; i < rules.size(); i++) {
            result.put(i, rules.get(i).name);
        }
        return result;
    }

    /**
     * Распределение продуктов по хранилищам.
     *
     * @param foods Список продуктов.
     */
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

    /**
     * Класс правила.
     */
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
