package ru.job4j.productstorage;

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

public class App {
    public static void main(String[] args) {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<? extends Food> impFoods = generateProducts();
        for (Food food : impFoods) {
            System.out.print(food.getName() + " = ");
            System.out.println(food.getExpiryPercentOfDate(LocalDate.now().plusDays(10)));
        }
        impFoods.forEach(warehouse::add);
        LocalDate date = LocalDate.now().plusDays(10);
        List<DistributionOperation> operations = List.of(
                new DistributeStorage(warehouse, food -> food.getExpiryPercentOfDate(date) <= 25),
                new DistributeStorage(shop, food -> food.getExpiryPercentOfDate(date) > 25
                        && food.getExpiryPercentOfDate(date) <= 75),
                new DistributeStorageChangeDiscount(shop, food -> food.getExpiryPercentOfDate(date) > 75
                        && food.getExpiryPercentOfDate(date) < 100, 5),
                new DistributeStorage(trash, food -> food.getExpiryPercentOfDate(date) >= 100)
        );
        ControlQuality controlQuality = new ControlQuality(operations);
        controlQuality.distributeFoodsIn(List.of(warehouse, shop));
        System.out.println("WH" + warehouse.getFoods());
        System.out.println("SH" + shop.getFoods());
        System.out.println("TRASH" + trash.getFoods());
    }

    private static List<? extends Food> generateProducts() {
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
