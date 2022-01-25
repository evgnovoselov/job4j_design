package ru.job4j.parkingcars.car;

import java.util.Objects;

public class PassengerCar implements Car {
    private static int count = 1;
    private int id = count++;
    public static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public PassengerCar getCopy() {
        PassengerCar copy = new PassengerCar();
        copy.id = this.id;
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassengerCar that = (PassengerCar) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
