package ru.job4j.productstorage.distributionoperation;

import ru.job4j.productstorage.food.Food;
import ru.job4j.productstorage.storage.Storage;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Класс перемещает продукты из from в to по условию where и меняет значение скидки.
 */
public class DistributeStorageChangeDiscount implements DistributionOperation {
    private Storage to;
    private Predicate<Food> where;
    private int discount;

    public DistributeStorageChangeDiscount(Storage to, Predicate<Food> where, int discount) {
        this.to = to;
        this.where = where;
        this.discount = discount;
    }

    @Override
    public Storage getTo() {
        return to;
    }

    @Override
    public void toDistribute(Storage from) {
        Iterator<Food> iterator = from.getFoods().iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (where.test(food)) {
                if (from.equals(to)) {
                    food.setDiscount(discount);
                    continue;
                }
                iterator.remove();
                food.setDiscount(discount);
                to.add(food);
            }
        }
    }
}
