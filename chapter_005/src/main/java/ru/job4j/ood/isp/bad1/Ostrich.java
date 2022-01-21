package ru.job4j.ood.isp.bad1;

/**
 * Класс страуса.
 */
public class Ostrich implements Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Страусы не летают.");
    }

    @Override
    public void swim() {
        throw new UnsupportedOperationException("Страусы не плавают.");
    }

    @Override
    public void run() {
        System.out.println("Бежим.");
    }
}
