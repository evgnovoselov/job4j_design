package ru.job4j.productstorage;

import ru.job4j.productstorage.distributionoperation.ControlQualityOperation;
import ru.job4j.productstorage.storage.Storage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер правил распределения продуктов.
 */
public class ControlQuality {
    private List<Rule> rules = new ArrayList<>();

    /**
     * Добавляет правило.
     *
     * @param name Имя правила.
     */
    public void addRule(String name, ControlQualityOperation operation) {
        Rule rule = new Rule(name, operation);
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
     * @param storages Список хранилещей для распределения продуктов.
     */
    public void distributeFoods(List<Storage> storages) {
        for (Storage storage : storages) {
            for (Rule rule : rules) {
                if (!storage.equals(rule.operation.getTo())) {
                    rule.operation.toDistribute(storage);
                }
            }
        }
    }

    /**
     * Класс правила.
     */
    private static class Rule {
        private String name;
        private ControlQualityOperation operation;

        public Rule(String name, ControlQualityOperation operation) {
            this.name = name;
            this.operation = operation;
        }
    }
}
