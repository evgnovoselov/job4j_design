package ru.job4j.productstorage;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Food> impFoods = generateProducts();
        LocalDate now = LocalDate.now();
        now = now.plusDays(2);
        System.out.println(impFoods.get(0).getExpiryPercentOfDate(now));
    }

    public static List<Food> generateProducts() {
        return new ArrayList<>(List.of(
                new Food(
                        "Креветки",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 23),
                        100,
                        0
                ),
                new Food(
                        "Форель",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 20),
                        100,
                        0
                ),
                new Food(
                        "Чипсы Принглс",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 20),
                        100,
                        0
                ),
                new Food(
                        "Конфеты",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 20),
                        100,
                        0
                ),
                new Food(
                        "Сникерс",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 20),
                        100,
                        0
                ),
                new Food(
                        "Яблоки",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 20),
                        100,
                        0
                )
        ));
    }
}
