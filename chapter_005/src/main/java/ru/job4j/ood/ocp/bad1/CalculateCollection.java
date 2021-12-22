package ru.job4j.ood.ocp.bad1;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Нарушает OCP, метод calculate для подсчета элементов в Set придется писать новый метод.
 * В calculate нужно использовать iterator.
 *
 * @param <T> Тип элементов для подсчета.
 */
public class CalculateCollection<T> {
    public T calculate(BiFunction<T, T, T> function, List<T> values) {
        T result = null;
        boolean firstStep = true;
        for (T value : values) {
            if (firstStep) {
                result = value;
                firstStep = false;
                continue;
            }
            result = function.apply(result, value);
        }
        return result;
    }

    public static void main(String[] args) {
        CalculateCollection<Integer> calculateCollection = new CalculateCollection<>();
        System.out.println(calculateCollection.calculate(
                Integer::sum,
                List.of(1, 2, 3, 4, 5)
        ));
    }
}
