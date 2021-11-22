package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Класс поиска в списке максимального значения и минимального.
 */
public class MaxMin {
    /**
     * Возвращает максимальное значение в списке.
     *
     * @param value      Список.
     * @param comparator Компаратор.
     * @param <T>        Тип значений.
     * @return Максимальное значение в списке.
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T result = null;
        var iterator = value.iterator();
        if (iterator.hasNext()) {
            result = iterator.next();
        }
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (comparator.compare(result, current) > 0) {
                result = current;
            }
        }
        return result;
    }

    /**
     * Возвращает минимальное значение в списке.
     *
     * @param value      Список.
     * @param comparator Компаратор.
     * @param <T>        Тип значений.
     * @return Минимальное значение в списке.
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return max(value, comparator.reversed());
    }
}
