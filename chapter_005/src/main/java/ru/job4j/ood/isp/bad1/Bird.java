package ru.job4j.ood.isp.bad1;

/**
 * Интерфейс нарушает принцип разделения интерфейсов (ISP) и получается довольно избыточным,
 * не все птицы летают или плавают, нужно разбить на интерфейсы: Flyable, Swimmable.
 */
public interface Bird {
    void fly();

    void swim();

    void run();
}
