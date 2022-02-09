package ru.job4j.productstorage;

import org.junit.Test;
import ru.job4j.productstorage.food.*;
import ru.job4j.productstorage.storage.Shop;
import ru.job4j.productstorage.storage.Storage;
import ru.job4j.productstorage.storage.Trash;
import ru.job4j.productstorage.storage.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ControlQualityTest {
    /**
     * Проверка, что при получении списка продуктов в магазине, в список напрямую нельзя добавить продукт.
     */
    @Test
    public void whenGetFoodsAndAddFoodThenNotHaveFood() {
        List<? extends Food> foods = generateProducts();
        List<Storage> storages = List.of(new Warehouse(), new Shop());
        for (Storage storage : storages) {
            foods.forEach(storage::add);
            List<Food> unexpected = storage.getFoods();
            List<Food> actual = storage.getFoods();
            actual.add(new Fruit("Яблоки 2",
                    LocalDate.now().minusDays(10),
                    LocalDate.now().plusDays(3),
                    100));
            assertNotEquals(unexpected.stream().map(this::foodFormatter).collect(Collectors.joining()),
                    actual.stream().map(this::foodFormatter).collect(Collectors.joining()));
        }
    }

    /**
     * Тестирование функционала перераспределения еды в хранилищах warehouse и shop.
     */
    @Test
    public void whenDistributeFoodsFromStoragesThenStoragesHaveFoods() {
        List<String> expected;
        List<String> actual;
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        List<? extends Food> foods = generateProducts();
        List<Storage> storages = List.of(warehouse, shop);
        ControlQuality controlQuality = new ControlQuality(storages);
        foods.forEach(controlQuality::distributeFood);
        expected = List.of("Чипсы Принглс : 14 : 0%");
        actual = getFormattedFoods(warehouse);
        assertEquals(expected, actual);
        expected = List.of("Креветки : 33 : 0%", "Форель : 83 : 10%", "Яблоки : 76 : 10%");
        actual = getFormattedFoods(shop);
        assertEquals(expected, actual);
    }

    /**
     * Проверка когда в хранилищах есть просроченные товары, они отправляются в хранилище trash.
     */
    @Test
    public void whenStoragesHaveTrashThenDeletedFoods() {
        List<String> expected;
        List<String> actual;
        List<? extends Food> foods = generateProducts();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        foods.forEach(controlQuality::distributeFood);
        expected = List.of("Чипсы Принглс : 14 : 0%");
        actual = getFormattedFoods(warehouse);
        assertEquals(expected, actual);
        expected = List.of("Креветки : 33 : 0%", "Форель : 83 : 10%", "Яблоки : 76 : 10%");
        actual = getFormattedFoods(shop);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash);
        assertEquals(expected, actual);
    }

    /**
     * Проверка работы когда в хранилищах есть товары необходимые для продажи со скидкой,
     * перемещение и выставление скидки.
     */
    @Test
    public void whenHaveFoodsNeedDiscount() {
        List<String> expected;
        List<String> actual;
        List<? extends Food> foods = generateProducts();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        foods.forEach(controlQuality::distributeFood);
        expected = List.of("Чипсы Принглс : 14 : 0%");
        actual = getFormattedFoods(warehouse);
        assertEquals(expected, actual);
        expected = List.of("Креветки : 33 : 0%", "Форель : 83 : 10%", "Яблоки : 76 : 10%");
        actual = getFormattedFoods(shop);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash);
        assertEquals(expected, actual);
    }

    /**
     * Проверяем работу метода перераспределения продуктов.
     */
    @Test
    public void whenFoodsResortThenChangeStorages() {
        List<String> expected;
        List<String> actual;
        List<? extends Food> foods = new ArrayList<>(List.of(
                new Seafood("Креветки",
                        LocalDate.now().minusDays(20),
                        LocalDate.now().plusDays(50),
                        100),
                new Seafood("Форель",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(50),
                        100),
                new Chips("Чипсы Принглс",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(50),
                        100),
                new Chocolate("Конфеты",
                        LocalDate.now().minusDays(20),
                        LocalDate.now().plusDays(50),
                        100),
                new Chocolate("Сникерс",
                        LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(50),
                        100),
                new Fruit("Яблоки",
                        LocalDate.now().minusDays(60),
                        LocalDate.now().plusDays(20),
                        100)
        ));
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        foods.forEach(controlQuality::distributeFood);
        warehouse.getFoods().stream().skip(1).forEach(food -> food.setExpiryDate(food.getExpiryDate().minusDays(45)));
        shop.getFoods().stream().skip(1).forEach(food -> food.setExpiryDate(food.getExpiryDate().minusDays(45)));
        controlQuality.resort();
        expected = List.of("Форель : 16 : 0%");
        actual = getFormattedFoods(warehouse);
        assertEquals(expected, actual);
        expected = List.of("Чипсы Принглс : 66 : 0%", "Сникерс : 66 : 0%", "Креветки : 28 : 0%", "Конфеты : 80 : 10%");
        actual = getFormattedFoods(shop);
        assertEquals(expected, actual);
        expected = List.of();
        actual = getFormattedFoods(trash);
        assertEquals(expected, actual);
    }

    private List<String> getFormattedFoods(Storage storage) {
        return storage.getFoods().stream()
                .map(food -> String.format("%s : %s : %s%%",
                        food.getName(),
                        storage.getExpiryPercentBy(food),
                        food.getDiscount()))
                .collect(Collectors.toList());
    }

    private String foodFormatter(Food food) {
        return String.format("Food: %s, %s, %s, %s, %s",
                food.getName(),
                food.getCreateDate(),
                food.getExpiryDate(),
                food.getPrice(),
                food.getDiscount());
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