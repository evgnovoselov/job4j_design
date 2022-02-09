package ru.job4j.productstorage;

import ru.job4j.productstorage.food.Food;
import ru.job4j.productstorage.storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер правил распределения продуктов.
 */
public class ControlQuality {
    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    /**
     * Распределение продукта по хранилищам.
     *
     * @param food Продукт для определения в хранилище.
     */
    public void distributeFood(Food food) {
        for (Storage storage : storages) {
            if (storage.add(food)) {
                break;
            }
        }
    }

    /**
     * Запуск перераспределения продуктов.
     */
    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Storage storage : storages) {
            foods.addAll(storage.getFoods());
            storage.getFoods().forEach(storage::remove);
        }
        foods.forEach(this::distributeFood);
    }
}
