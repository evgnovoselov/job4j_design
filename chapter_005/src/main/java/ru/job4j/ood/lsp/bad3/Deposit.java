package ru.job4j.ood.lsp.bad3;

public class Deposit {
    protected double amount = 0;

    public Deposit(double amount) {
        validate(amount);
        this.amount = amount;
    }

    protected void validate(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Недопустимая сумма!");
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        validate(amount);
        this.amount = amount;
    }
}
