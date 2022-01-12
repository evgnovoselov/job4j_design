package ru.job4j.ood.lsp.bad2;

public class DiscountStore {
    protected int minimumSum;
    protected int discount;

    public DiscountStore(int minimumSum, int discount) {
        this.minimumSum = minimumSum;
        this.discount = discount;
    }

    public double sale(int sumBasket) {
        if (sumBasket < 0) {
            throw new IllegalArgumentException("Неверная сумма корзины товаров");
        }
        double result = sumBasket;
        if (sumBasket < minimumSum) {
            throw new IllegalArgumentException("Клиент не набрал нужную сумму для продажи");
        }
        result -= result * discount / 100;
        return result;
    }
}
