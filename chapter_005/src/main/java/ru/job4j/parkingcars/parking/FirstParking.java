package ru.job4j.parkingcars.parking;

import ru.job4j.parkingcars.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        return null;
    }

    @Override
    public boolean isThereSpaceForCar(Car car) {
        return false;
    }

    @Override
    public boolean addCar(Car car) {
        return true;
    }
}
