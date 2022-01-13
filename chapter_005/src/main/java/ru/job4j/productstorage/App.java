package ru.job4j.productstorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Food> impFoods = generateProducts();
        LocalDate date = LocalDate.now();
        date = date.plusDays(10);
        for (Food food : impFoods) {
            System.out.println(food.getExpiryPercentOfDate(date));
        }
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
