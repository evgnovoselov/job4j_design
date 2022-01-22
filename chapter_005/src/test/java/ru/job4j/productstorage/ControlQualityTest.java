package ru.job4j.productstorage;

import org.junit.Test;
import ru.job4j.productstorage.distributionoperation.DistributeStorage;
import ru.job4j.productstorage.distributionoperation.DistributeStorageChangeDiscount;
import ru.job4j.productstorage.distributionoperation.DistributionOperation;
import ru.job4j.productstorage.food.*;
import ru.job4j.productstorage.storage.Shop;
import ru.job4j.productstorage.storage.Storage;
import ru.job4j.productstorage.storage.Trash;
import ru.job4j.productstorage.storage.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ControlQualityTest {
    /**
     * Тестирование функционала перераспределения еды в хранилищах warehouse и shop.
     */
    @Test
    public void whenDistributeFoodsFromStoragesThenStoragesHaveFoods() {
        List<? extends Food> foods = generateProducts();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        warehouse.add(foods.get(0));
        shop.add(foods.get(1));
        shop.add(foods.get(2));
        warehouse.add(foods.get(3));
        warehouse.add(foods.get(4));
        shop.add(foods.get(5));
        List<String> expected = List.of("Креветки : 33 : 0%", "Конфеты : 100 : 0%", "Сникерс : 100 : 0%");
        List<String> actual = getFormattedFoods(warehouse, date);
        assertEquals(expected, actual);
        expected = List.of("Форель : 83 : 0%", "Чипсы Принглс : 14 : 0%", "Яблоки : 76 : 0%");
        actual = getFormattedFoods(shop, date);
        assertEquals(expected, actual);
        List<DistributionOperation> operations = List.of(
                new DistributeStorage(warehouse, food -> food.getExpiryPercentOfDate(date) <= 25),
                new DistributeStorage(shop, food -> food.getExpiryPercentOfDate(date) > 25
                        && food.getExpiryPercentOfDate(date) <= 75)
        );
        ControlQuality controlQuality = new ControlQuality(operations);
        controlQuality.distributeFoodsIn(List.of(warehouse, shop));
        expected = List.of("Конфеты : 100 : 0%", "Сникерс : 100 : 0%", "Чипсы Принглс : 14 : 0%");
        actual = getFormattedFoods(warehouse, date);
        assertEquals(expected, actual);
        expected = List.of("Форель : 83 : 0%", "Яблоки : 76 : 0%", "Креветки : 33 : 0%");
        actual = getFormattedFoods(shop, date);
        assertEquals(expected, actual);
    }

    /**
     * Проверка когда в хранилищах есть просроченные товары, они отправляются в хранилище trash.
     */
    @Test
    public void whenStoragesHaveTrashThenDeletedFoods() {
        List<? extends Food> foods = generateProducts();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        warehouse.add(foods.get(0));
        shop.add(foods.get(1));
        shop.add(foods.get(2));
        warehouse.add(foods.get(3));
        shop.add(foods.get(4));
        shop.add(foods.get(5));
        List<String> expected = List.of("Креветки : 33 : 0%", "Конфеты : 100 : 0%");
        List<String> actual = getFormattedFoods(warehouse, date);
        assertEquals(expected, actual);
        expected = List.of("Форель : 83 : 0%", "Чипсы Принглс : 14 : 0%", "Сникерс : 100 : 0%", "Яблоки : 76 : 0%");
        actual = getFormattedFoods(shop, date);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash, date);
        assertEquals(expected, actual);
        List<DistributionOperation> operations = List.of(
                new DistributeStorage(warehouse, food -> food.getExpiryPercentOfDate(date) <= 25),
                new DistributeStorage(shop, food -> food.getExpiryPercentOfDate(date) > 25
                        && food.getExpiryPercentOfDate(date) <= 75),
                new DistributeStorage(trash, food -> food.getExpiryPercentOfDate(date) >= 100)
        );
        ControlQuality controlQuality = new ControlQuality(operations);
        controlQuality.distributeFoodsIn(List.of(warehouse, shop));
        expected = List.of("Чипсы Принглс : 14 : 0%");
        actual = getFormattedFoods(warehouse, date);
        assertEquals(expected, actual);
        expected = List.of("Форель : 83 : 0%", "Яблоки : 76 : 0%", "Креветки : 33 : 0%");
        actual = getFormattedFoods(shop, date);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash, date);
        assertEquals(expected, actual);
    }

    /**
     * Проверка работы когда в хранилищах есть товары необходимые для продажи со скидкой,
     * перемещение и выставление скидки.
     */
    @Test
    public void whenHaveFoodsNeedDiscount() {
        List<? extends Food> foods = generateProducts();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        warehouse.add(foods.get(0));
        shop.add(foods.get(1));
        shop.add(foods.get(2));
        warehouse.add(foods.get(3));
        shop.add(foods.get(4));
        warehouse.add(foods.get(5));
        List<String> expected = List.of("Креветки : 33 : 0%", "Конфеты : 100 : 0%", "Яблоки : 76 : 0%");
        List<String> actual = getFormattedFoods(warehouse, date);
        assertEquals(expected, actual);
        expected = List.of("Форель : 83 : 0%", "Чипсы Принглс : 14 : 0%", "Сникерс : 100 : 0%");
        actual = getFormattedFoods(shop, date);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash, date);
        assertEquals(expected, actual);
        List<DistributionOperation> operations = List.of(
                new DistributeStorageChangeDiscount(shop, food -> food.getExpiryPercentOfDate(date) > 75
                        && food.getExpiryPercentOfDate(date) < 100, 5)
        );
        ControlQuality controlQuality = new ControlQuality(operations);
        controlQuality.distributeFoodsIn(List.of(warehouse, shop));
        expected = List.of("Креветки : 33 : 0%", "Конфеты : 100 : 0%");
        actual = getFormattedFoods(warehouse, date);
        assertEquals(expected, actual);
        expected = List.of("Форель : 83 : 5%", "Чипсы Принглс : 14 : 0%", "Сникерс : 100 : 0%", "Яблоки : 76 : 5%");
        actual = getFormattedFoods(shop, date);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash, date);
        assertEquals(expected, actual);
    }

    private List<String> getFormattedFoods(Storage storage, LocalDate date) {
        return storage.getFoods().stream()
                .map(food -> String.format("%s : %s : %s%%",
                        food.getName(),
                        food.getExpiryPercentOfDate(date),
                        food.getDiscount()))
                .collect(Collectors.toList());
    }

    private static List<? extends Food> generateProducts() {
        return new ArrayList<>(List.of(
                new Seafood("Креветки",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(20),
                        100),
                new Seafood("Форель",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(2),
                        100),
                new Chips("Чипсы Принглс",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(60),
                        100),
                new Chocolate("Конфеты",
                        LocalDate.now().minusDays(10),
                        LocalDate.now(),
                        100),
                new Chocolate("Сникерс",
                        LocalDate.now().minusDays(10),
                        LocalDate.now(),
                        100),
                new Fruit("Яблоки",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(3),
                        100)));
    }
}