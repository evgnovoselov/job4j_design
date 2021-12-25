package ru.job4j.ood.ocp.bad3;

/**
 * Неправильно, нельзя расширять кошку, собакой! Собака NOT is a кошка
 */
public class Dog extends Cat {
    public Dog(String name) {
        super(name);
        setType("Собака");
    }

    public Dog(String name, int qtyPaw, boolean hasTail) {
        super(name, qtyPaw, hasTail);
    }

    @Override
    public String say() {
        return "гав-гав";
    }
}
