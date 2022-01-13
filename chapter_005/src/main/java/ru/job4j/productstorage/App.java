package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Storage, Predicate<Integer>> storageRules = new HashMap<>(Map.of(
                warehouse, percent -> percent.compareTo(25) < 1,
                shop, percent -> percent.compareTo(25) > 0 && percent.compareTo(100) < 0,
                trash, percent -> percent.compareTo(100) >= 0
        ));
        ControlQuality controlQuality = new ControlQuality(storageRules);
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
