package ru.job4j.productstorage.distributionoperation;

import ru.job4j.productstorage.food.Food;
import ru.job4j.productstorage.storage.Storage;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Класс перемещает продукты из from в to по условию where.
 */
public class DistributeStorage implements DistributionOperation {
    private Storage to;
    private Predicate<Food> where;

    public DistributeStorage(Storage to, Predicate<Food> where) {
        this.to = to;
        this.where = where;
    }

    @Override
    public Storage getTo() {
        return to;
    }

    @Override
    public void toDistribute(Storage from) {
        Iterator<Food> iterator = from.getFoods().iterator();
        while (iterator.hasNext()) {
            if (from.equals(to)) {
                break;
            }
            Food food = iterator.next();
            if (where.test(food)) {
                iterator.remove();
                to.add(food);
            }
        }
    }
}
