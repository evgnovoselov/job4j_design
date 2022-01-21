package ru.job4j.parkingcars;

import org.junit.Test;
import ru.job4j.parkingcars.car.Car;
import ru.job4j.parkingcars.car.PassengerCar;
import ru.job4j.parkingcars.car.Truck;
import ru.job4j.parkingcars.parking.FirstParking;
import ru.job4j.parkingcars.parking.Parking;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {
    /**
     * Тестирование получение свободного пространства для легковых машин после добавления 1 машины.
     */
    @Test
    public void whenAddPassengerCarToParkingThenCheckFreeSpace() {
        Parking parking = new FirstParking(3, 1);
        parking.addCar(new PassengerCar());
        assertEquals(2, parking.getPassengerCarFreeSpaces());
        assertEquals(1, parking.getTruckFreeSpaces());
    }

    /**
     * Тестирование получение свободного пространства для грузовых машин после добавления 1 груз. машины размеров 3.
     */
    @Test
    public void whenAddTruckToParkingThenCheckFreeSpace() {
        Parking parking = new FirstParking(3, 5);
        parking.addCar(new Truck(3));
        assertEquals(3, parking.getPassengerCarFreeSpaces());
        assertEquals(4, parking.getTruckFreeSpaces());
    }

    /**
     * Тестирование добавления двух легковых машин и одной грузовой.
     * Машины поместятся на парковку.
     */
    @Test
    public void whenParkingHaveSpaces2PassengerAnd1TruckThenParkingHaveCars() {
        Parking parking = new FirstParking(2, 1);
        List<Car> cars = new ArrayList<>(List.of(
                new PassengerCar(),
                new PassengerCar(),
                new Truck(2)
        ));
        assertTrue(parking.addCar(cars.get(0)));
        assertTrue(parking.addCar(cars.get(1)));
        assertTrue(parking.addCar(cars.get(2)));
        assertEquals(0, parking.getPassengerCarFreeSpaces());
        assertEquals(0, parking.getTruckFreeSpaces());
        assertEquals(cars, parking.getCars(car -> true));
    }

    /**
     * Добавление на парковку с местами 2 легковых и 1 грузовых машины, две грузовых машины с размером 4 и 2.
     * Машины поместятся на парковку.
     */
    @Test
    public void whenParkingSpaces2PassengerCarAnd1TruckAdd2TrucksThenHave2Trucks() {
        Parking parking = new FirstParking(2, 1);
        List<Car> cars = new ArrayList<>(List.of(
                new Truck(4),
                new Truck(2)
        ));
        assertTrue(parking.isThereSpaceForCar(cars.get(0)));
        assertTrue(parking.addCar(cars.get(0)));
        assertTrue(parking.isThereSpaceForCar(cars.get(1)));
        assertTrue(parking.addCar(cars.get(1)));
        assertEquals(0, parking.getPassengerCarFreeSpaces());
        assertEquals(0, parking.getTruckFreeSpaces());
        assertEquals(cars, parking.getCars(car -> true));
    }

    /**
     * Добавление на парковку с местами 2 легковых и 1 грузовых машины, две грузовых машины с размером 4 и 3.
     * Машины не поместятся на парковке, будет добавлен только одна грузовая машина.
     */
    @Test
    public void whenParkingSpaces1PassengerCarAnd1TruckAdd2TrucksThenNotHaveFreeSpaceForTruck() {
        Parking parking = new FirstParking(2, 1);
        List<Car> cars = new ArrayList<>(List.of(
                new Truck(4),
                new Truck(3)
        ));
        assertTrue(parking.addCar(cars.get(0)));
        assertFalse(parking.isThereSpaceForCar(cars.get(1)));
        assertFalse(parking.addCar(cars.get(1)));
        assertEquals(2, parking.getPassengerCarFreeSpaces());
        assertEquals(0, parking.getTruckFreeSpaces());
        assertEquals(cars, List.of(cars.get(0)));
    }
}