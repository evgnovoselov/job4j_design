package ru.job4j.productstorage;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Food> impFoods = new ArrayList<>(List.of(
                new Food(
                        "Креветки",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 23),
                        100,
                        5
                ),
                new Food(
                        "Креветки Имперские",
                        LocalDate.of(2022, Month.JANUARY, 13),
                        LocalDate.of(2022, Month.JANUARY, 20),
                        100,
                        5
                )
        ));
        LocalDate now = LocalDate.now();
        now = now.plusDays(2);
        System.out.println(impFoods.get(0).getExpiryPercentOfDate(now));
    }
}
