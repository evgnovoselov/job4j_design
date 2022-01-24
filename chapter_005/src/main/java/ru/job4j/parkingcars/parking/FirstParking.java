package ru.job4j.parkingcars.parking;

import ru.job4j.parkingcars.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FirstParking implements Parking {
    /**
     * Свободное пространство для легковых машин.
     */
    private int passengerCarFreeSpaces;
    /**
     * Свободное простанство для грузовых машин.
     */
    private int truckFreeSpaces;
    /**
     * Машины на парковке.
     */
    private List<Car> cars = new ArrayList<>();

    /**
     * @param passengerCarFreeSpaces Свободное пространство для легковых машин.
     * @param truckFreeSpaces        Свободное пространство для грузовых машин.
     */
    public FirstParking(int passengerCarFreeSpaces, int truckFreeSpaces) {
        this.passengerCarFreeSpaces = passengerCarFreeSpaces;
        this.truckFreeSpaces = truckFreeSpaces;
    }

    @Override
    public int getPassengerCarFreeSpaces() {
        return passengerCarFreeSpaces;
    }

    @Override
    public int getTruckFreeSpaces() {
        return truckFreeSpaces;
    }

    @Override
    public List<Car> getCars(Predicate<Car> predicate) {
        return cars.stream().filter(predicate).map(Car::getCopy).collect(Collectors.toList());
    }

    @Override
    public boolean isThereSpaceForCar(Car car) {
        boolean result = false;
        if (car.getSize() > 1 && truckFreeSpaces > 0) {
            result = true;
        } else if (passengerCarFreeSpaces >= car.getSize()) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean addCar(Car car) {
        boolean result = false;
        if (car.getSize() > 1 && truckFreeSpaces >= 1) {
            truckFreeSpaces -= 1;
            cars.add(car);
            result = true;
        }
        if (!result && passengerCarFreeSpaces >= car.getSize()) {
            passengerCarFreeSpaces -= car.getSize();
            cars.add(car);
            result = true;
        }
        return result;
    }
}
