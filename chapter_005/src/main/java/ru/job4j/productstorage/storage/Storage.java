package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.util.List;

/**
 * Интерыейс хранилища.
 */
public interface Storage {
    /**
     * Метод проверяет можно ли положить продукт в хранилище.
     *
     * @param food Продукт.
     * @return Можно ли положить в хранилище.
     */
    boolean accept(Food food);

    /**
     * Добавляет продукт в хранилище.
     *
     * @param food Продукт.
     */
    void add(Food food);

    /**
     * Удаляет продукт из хранилища.
     *
     * @param food Продукт.
     * @return Получилось ли удалить продукт.
     */
    boolean remove(Food food);

    /**
     * Возвращает продукты в хранилище.
     *
     * @return Список продуктов в хранилище.
     */
    List<Food> getFoods();
}
