package ru.job4j.parkingcars.car;

public class Truck implements Car {
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
