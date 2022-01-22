package ru.job4j.productstorage.storage;

import ru.job4j.productstorage.food.Food;

import java.time.LocalDate;
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
     * @return Получилось ли добавить продукт в хранилище.
     */
    boolean add(Food food);

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

    /**
     * Получение процента истечения срока годности продукта.
     *
     * @param food Продукт.
     * @return Процент истечения срока годности продукта.
     */
    default int getExpiryPercentBy(Food food) {
        long lifeDays = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long passedDays = lifeDays - (food.getExpiryDate().toEpochDay() - LocalDate.now().toEpochDay());
        return (int) (passedDays * 100 / lifeDays);
    }
}
