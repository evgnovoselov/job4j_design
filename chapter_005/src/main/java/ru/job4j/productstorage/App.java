package ru.job4j.productstorage;

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
        List<? extends Food> impFoods = generateProducts();
        for (Food food : impFoods) {
            System.out.print(food.getName() + " = ");
            System.out.println(food.getExpiryPercentOfDate(LocalDate.now().plusDays(10)));
        }
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality();
        System.out.println(controlQuality.getRules());
        LocalDate date = LocalDate.now().plusDays(10);
        controlQuality.addRule("На склад срок годности израсходован меньше чем на 25%",
                warehouse,
                food -> food.getExpiryPercentOfDate(date) <= 25,
                0);
        controlQuality.addRule("В магазин срок годности от 25% до 75%",
                shop,
                food -> food.getExpiryPercentOfDate(date) > 25
                        && food.getExpiryPercentOfDate(date) <= 75,
                0);
        controlQuality.addRule("В магазин со скидкой. Срок годности больше 75%",
                shop,
                food -> food.getExpiryPercentOfDate(date) > 75
                        && food.getExpiryPercentOfDate(date) < 100,
                5);
        controlQuality.addRule("Утилизация, срок годности вышел",
                trash,
                food -> food.getExpiryPercentOfDate(date) >= 100,
                0);
        System.out.println(controlQuality.getRules());
        controlQuality.distributeFoods(impFoods);
        System.out.println("WH" + warehouse.getFoods());
        System.out.println("SH" + shop.getFoods());
        System.out.println("TRASH" + trash.getFoods());
    }

    public static List<? extends Food> generateProducts() {
        return new ArrayList<>(List.of(
                new Seafood(
                        "Креветки",
                        LocalDate.now(),
                        LocalDate.now().plusDays(30),
                        100,
                        0
                ),
                new Seafood(
                        "Форель",
                        LocalDate.now(),
                        LocalDate.now().plusDays(12),
                        100,
                        0
                ),
                new Chips(
                        "Чипсы Принглс",
                        LocalDate.now(),
                        LocalDate.now().plusDays(70),
                        100,
                        0
                ),
                new Chocolate(
                        "Конфеты",
                        LocalDate.now(),
                        LocalDate.now().plusDays(10),
                        100,
                        0
                ),
                new Chocolate(
                        "Сникерс",
                        LocalDate.now(),
                        LocalDate.now().plusDays(10),
                        100,
                        0
                ),
                new Fruit("Яблоки",
                        LocalDate.now(),
                        LocalDate.now().plusDays(13),
                        100,
                        0)
        ));
    }
}
