package ru.job4j.parkingcars.car;

/**
 * Машина.
 */
public interface Car {
    /**
     * Получение размеры машины.
     *
     * @return размер машины.
     */
    int getSize();

    /**
     * Возвращение копии машины.
     *
     * @return Копия машины.
     */
    Car getCopy();
}
