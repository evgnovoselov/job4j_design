package ru.job4j.ood.isp.bad2;

public class FreeParking implements Parking {
    @Override
    public void parkCar() {

    }

    @Override
    public void returnParkCar() {

    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public int calculateFee(String numberCar) {
        return 0;
    }

    @Override
    public void payment(String numberCar) {
        throw new UnsupportedOperationException("Это бесплатная парковка");
    }
}
