package ru.job4j.ood.ocp.bad3;

import java.util.List;

/**
 * Нарушение OCP.
 * Неправильное расширение кошки собакой. Нужно реализовывать через абстракцию животного.
 */
public class AnimalSay {
    public static void main(String[] args) {
        List<Cat> cats = List.of(
                new Cat("Барсик", 4, true),
                new Cat("Алиса", 4, false),
                new Dog("Деймон", 4, false)
        );
        cats.forEach(cat -> System.out.printf(
                "%s %s говорит %s, у него %s лапы и хвост у него %s%n",
                cat.getType(), cat.getName(), cat.say(), cat.getQtyPaw(), cat.isHasTail()
        ));
    }
}
