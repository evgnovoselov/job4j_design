package ru.job4j.parkingcars.car;

import java.util.Objects;

public class Truck implements Car {
    private static int count = 1;
    private int id = count++;
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Truck getCopy() {
        Truck copy = new Truck(size);
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
        Truck truck = (Truck) o;
        return id == truck.id && size == truck.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }
}
