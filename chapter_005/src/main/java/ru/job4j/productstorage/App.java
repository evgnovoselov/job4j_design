package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        List<Food> impFoods = generateProducts();
        for (Food food : impFoods) {
            System.out.print(food.getName() + " = ");
            System.out.println(food.getExpiryPercentOfDate(LocalDate.now().plusDays(10)));
        }
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality();
        System.out.println(controlQuality.getRules());
        controlQuality.addRule(
                "На склад срок годности израсходован меньше чем на 25%",
                warehouse,
                percent -> percent.compareTo(25) < 1,
                0
        );
        controlQuality.addRule(
                "В магазин срок годности от 25% до 75%",
                shop,
                percent -> percent.compareTo(25) > 0 && percent.compareTo(75) <= 0,
                0
        );
        controlQuality.addRule(
                "В магазин со скидкой. Срок годности больше 75%",
                shop,
                percent -> percent.compareTo(75) > 0 && percent.compareTo(100) < 0,
                5
        );
        controlQuality.addRule(
                "Утилизация, срок годности вышел",
                trash,
                percent -> percent.compareTo(100) >= 0,
                0
        );
        System.out.println(controlQuality.getRules());
        controlQuality.distributeFoods(LocalDate.now().plusDays(10), impFoods);
        System.out.println("WH" + warehouse.getFoods());
        System.out.println("SH" + shop.getFoods());
        System.out.println("TRASH" + trash.getFoods());
    }

    public static List<Food> generateProducts() {
        return new ArrayList<>(List.of(
                new Food(
                        "Креветки",
                        LocalDate.now(),
                        LocalDate.now().plusDays(30),
                        100,
                        0
                ),
                new Food(
                        "Форель",
                        LocalDate.now(),
                        LocalDate.now().plusDays(12),
                        100,
                        0
                ),
                new Food(
                        "Чипсы Принглс",
                        LocalDate.now(),
                        LocalDate.now().plusDays(70),
                        100,
                        0
                ),
                new Food(
                        "Конфеты",
                        LocalDate.now(),
                        LocalDate.now().plusDays(10),
                        100,
                        0
                ),
                new Food(
                        "Сникерс",
                        LocalDate.now(),
                        LocalDate.now().plusDays(10),
                        100,
                        0
                ),
                new Food(
                        "Яблоки",
                        LocalDate.now(),
                        LocalDate.now().plusDays(13),
                        100,
                        0
                )
        ));
    }
}
