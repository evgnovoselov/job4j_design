package ru.job4j.parkingcars;

import ru.job4j.parkingcars.car.Car;
import ru.job4j.parkingcars.parking.Parking;

import java.util.List;
import java.util.function.Predicate;

/**
 * Сервис учета парковок.
 */
public interface ParkingService {
    /**
     * Получить все парковки.
     *
     * @return Парковки
     */
    List<Parking> getAllParking();

    /**
     * Получить общее количество мест.
     *
     * @return Количество мест.
     */
    int getSpaces();

    /**
     * Получить общее количество свободных мест.
     *
     * @return Количество свободных мест.
     */
    int getFreeSpaces();

    /**
     * Получить общее количество свободных мест для легковых машин.
     *
     * @return Количество свободных мест.
     */
    int getFreePassengerCarSpaces();

    /**
     * Могут ли парковки принять машину.
     *
     * @param car Машина.
     * @return Возможность завезти машину на парковку
     */
    boolean isThereSpaceForCar(Car car);

    /**
     * Завести машину на свободную парковку.
     *
     * @param car Машина.
     * @return Получилось ли ей заехать.
     */
    boolean addCar(Car car);

    /**
     * Получить машины по фильтру.
     *
     * @return Список машин.
     */
    List<Car> getCars(Predicate<Car> predicate);
}
