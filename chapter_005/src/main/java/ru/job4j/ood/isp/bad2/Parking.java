package ru.job4j.ood.isp.bad2;

/**
 * Интерфейс избыточен и нарушает принцип разделения интерфейсов ISP. Тем что не все парковки платные.
 */
public interface Parking {
    void parkCar();

    void returnParkCar();

    int getCapacity();

    int calculateFee(String numberCar);

    void payment(String numberCar);

}
