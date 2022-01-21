package ru.job4j.ood.isp.bad2;

public class BoxParking implements Parking {
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
        return 10;
    }

    @Override
    public void payment(String numberCar) {
        System.out.printf("С вас %s%n", calculateFee("123 77"));
    }
}
