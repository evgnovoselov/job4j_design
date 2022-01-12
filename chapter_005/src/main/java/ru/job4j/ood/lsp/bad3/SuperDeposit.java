package ru.job4j.ood.lsp.bad3;

public class SuperDeposit extends Deposit {
    public SuperDeposit(double amount) {
        super(amount);
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
