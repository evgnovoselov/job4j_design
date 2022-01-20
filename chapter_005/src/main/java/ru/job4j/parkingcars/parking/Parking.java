package ru.job4j.parkingcars.parking;

import ru.job4j.parkingcars.car.Car;

import java.util.List;
import java.util.function.Predicate;

/**
 * Парковка.
 */
public interface Parking {
    /**
     * Получение свободных мест парковки для легковых машин.
     *
     * @return Свободных мест на парковке.
     */
    int getPassengerCarFreeSpaces();

    /**
     * Получение свободных мест для грузовых машин.
     *
     * @return Количество свободных мест для грузовых машин на парковке.
     */
    int getTruckFreeSpaces();

    /**
     * Получение машин по условию.
     *
     * @return Машины на парковке.
     */
    List<Car> getCars(Predicate<Car> predicate);

    /**
     * Есть ли место для машины.
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
