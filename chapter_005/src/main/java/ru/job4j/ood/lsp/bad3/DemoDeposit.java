package ru.job4j.ood.lsp.bad3;

/**
 * Пример нарушения принципа LSP, нарушается Инвариантность - все условия базового класса
 * также должны быть сохранены и в подклассе. SuperDeposit не использовал проверку на отрицательное значение суммы.
 * Решение, использовать приватные модификаторы доступа в базовом классе.
 */
public class DemoDeposit {
    public static void main(String[] args) {
        Deposit deposit = new SuperDeposit(1000);
        deposit.setAmount(deposit.getAmount() - 1200);
        System.out.println(deposit.getAmount());
    }
}
