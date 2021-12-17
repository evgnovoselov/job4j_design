package ru.job4j.ood.srp.bad1;

/**
 * Неправильный SRP, интерфейс нарушает единственность ответственности.
 * Интерфейс бойлера, по мимо установки и выдачи температуры отправляет температуру.
 */
public interface Boiler {
    void heat(int degree);

    int getDegree();

    void sendDegree(int degree);
}
