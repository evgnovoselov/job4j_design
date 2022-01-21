package ru.job4j.ood.isp.bad1;

/**
 * Класс утки.
 */
public class Duck implements Bird {
    @Override
    public void fly() {
        System.out.println("Летает.");
    }

    @Override
    public void swim() {
        System.out.println("Плавает.");
    }

    @Override
    public void run() {
        System.out.println("Идет.");
    }
}
