package ru.job4j.ood.isp.bad1;

/**
 * Класс пингвина.
 */
public class Penguin implements Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Не может летать.");
    }

    @Override
    public void swim() {
        System.out.println("Плывет.");
    }

    @Override
    public void run() {
        System.out.println("Идет.");
    }
}
