package ru.job4j.ood.srp.bad1;

/**
 * Неправильный SRP.
 * Класс котла. Не должен реализовывать отправку температуры, нарушает единственность ответственности.
 */
public class GasBoiler implements Boiler {
    private int degree = 24;

    @Override
    public void heat(int degree) {
        this.degree = degree;
    }

    @Override
    public int getDegree() {
        return degree;
    }

    @Override
    public void sendDegree(int degree) {
        System.out.println(degree);
    }

    public static void main(String[] args) {
        Boiler boiler = new GasBoiler();
        boiler.sendDegree(boiler.getDegree());
        boiler.heat(30);
        boiler.sendDegree(boiler.getDegree());
    }
}
