package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

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
        return compareBy(value, (t, t2) -> comparator.compare(t, t2) > 0);
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
        return compareBy(value, (t, t2) -> comparator.compare(t, t2) < 0);
    }

    /**
     * Сравнение по условию.
     *
     * @param value       Список.
     * @param biPredicate Предикат.
     * @param <T>         Тип значений.
     * @return Возвращаем значение сравненное по предикату.
     */
    public <T> T compareBy(List<T> value, BiPredicate<T, T> biPredicate) {
        T result = null;
        var iterator = value.iterator();
        if (iterator.hasNext()) {
            result = iterator.next();
        }
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (biPredicate.test(current, result)) {
                result = current;
            }
        }
        return result;
    }
}
