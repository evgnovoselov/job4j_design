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
        LocalDate date = LocalDate.now().plusDays(10);
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        List<? extends Food> foods = generateProducts();
        warehouse.add(foods.get(0));
        shop.add(foods.get(1));
        shop.add(foods.get(2));
        warehouse.add(foods.get(3));
        warehouse.add(foods.get(4));
        shop.add(foods.get(5));
        List<String> expected = List.of("Креветки : 33",
                "Конфеты : 100",
                "Сникерс : 100");
        List<String> actual = warehouse.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
        expected = List.of("Форель : 83",
                "Чипсы Принглс : 14",
                "Яблоки : 76");
        actual = shop.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
        List<DistributionOperation> operations = List.of(
                new DistributeStorage(warehouse, food -> food.getExpiryPercentOfDate(date) <= 25),
                new DistributeStorage(shop, food -> food.getExpiryPercentOfDate(date) > 25
                        && food.getExpiryPercentOfDate(date) <= 75)
        );
        ControlQuality controlQuality = new ControlQuality(operations);
        controlQuality.distributeFoodsIn(List.of(warehouse, shop));
        expected = List.of("Конфеты : 100", "Сникерс : 100", "Чипсы Принглс : 14");
        actual = warehouse.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
        expected = List.of("Форель : 83",
                "Яблоки : 76",
                "Креветки : 33");
        actual = shop.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    /**
     * Проверка когда в хранилищах есть просроченные товары, они отправляются в хранилище trash.
     */
    public void whenStoragesHaveTrashThenDeletedFoods() {
        LocalDate date = LocalDate.now().plusDays(10);
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<? extends Food> foods = generateProducts();
        warehouse.add(foods.get(0));
        shop.add(foods.get(1));
        shop.add(foods.get(2));
        warehouse.add(foods.get(3));
        warehouse.add(foods.get(4));
        warehouse.add(foods.get(5));
        List<String> expected = List.of("Креветки : 33",
                "Форель : 83",
                "Чипсы Принглс : 14",
                "Конфеты : 100",
                "Сникерс : 100",
                "Яблоки : 76");
        List<String> actual = warehouse.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
        List<DistributionOperation> operations = List.of(
                new DistributeStorage(warehouse, food -> food.getExpiryPercentOfDate(date) <= 25),
                new DistributeStorage(shop, food -> food.getExpiryPercentOfDate(date) > 25
                        && food.getExpiryPercentOfDate(date) <= 75)
        );
        ControlQuality controlQuality = new ControlQuality(operations);
        controlQuality.distributeFoodsIn(List.of(warehouse, shop));
        expected = List.of("Форель : 83", "Чипсы Принглс : 14", "Конфеты : 100", "Сникерс : 100", "Яблоки : 76");
        actual = warehouse.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
        expected = List.of("Креветки : 33");
        actual = shop.getFoods().stream()
                .map(food -> String.format("%s : %s", food.getName(), food.getExpiryPercentOfDate(date)))
                .collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    public static List<? extends Food> generateProducts() {
        return new ArrayList<>(List.of(
                new Seafood("Креветки",
                        LocalDate.now(),
                        LocalDate.now().plusDays(30),
                        100),
                new Seafood("Форель",
                        LocalDate.now(),
                        LocalDate.now().plusDays(12),
                        100),
                new Chips("Чипсы Принглс",
                        LocalDate.now(),
                        LocalDate.now().plusDays(70),
                        100),
                new Chocolate("Конфеты",
                        LocalDate.now(),
                        LocalDate.now().plusDays(10),
                        100),
                new Chocolate("Сникерс",
                        LocalDate.now(),
                        LocalDate.now().plusDays(10),
                        100),
                new Fruit("Яблоки",
                        LocalDate.now(),
                        LocalDate.now().plusDays(13),
                        100)));
    }
}