package ru.job4j.ood.srp.sample;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator<Integer> {
    @Override
    public Integer generate() {
        return new Random().nextInt();
    }
}
