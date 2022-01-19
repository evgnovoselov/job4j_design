package ru.job4j.parkingcars.parking;

import ru.job4j.parkingcars.car.Car;

import java.util.List;
import java.util.function.Predicate;

/**
 * Парковка.
 */
public interface Parking {
    /**
     * Получение размера парковки.
     *
     * @return Размер парковки.
     */
    int getSpaces();

    /**
     * Получение свободных мест парковки.
     *
     * @return Свободных мест на парковке.
     */
    int getFreeSpaces();

    /**
     * Получение машин по условию.
     *
     * @return Машины на парковке.
     */
    List<Car> getCars(Predicate<Car> predicate);

    /**
     * Места только для грузовых машин.
     *
     * @return Места только для грузовых машин.
     */
    boolean isSpacesForTruckCar();

    /**
     * Есть ли места для машины.
     *
     * @param car Машина
     * @return Есть ли место.
     */
    boolean isThereSpaceForCar(Car car);

    /**
     * @param car
     * @return
     */
    boolean addCar(Car car);
}
