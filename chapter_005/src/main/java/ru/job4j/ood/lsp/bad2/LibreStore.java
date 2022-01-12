package ru.job4j.ood.lsp.bad2;

/**
 * Нарушение принципа LSP. Постусловие не могут быть ослаблены в подклассе.
 */
public class LibreStore extends DiscountStore {
    public LibreStore(int minimumSum, int discount) {
        super(minimumSum, discount);
    }

    @Override
    public double sale(int sumBasket) {
        if (sumBasket < 0) {
            throw new IllegalArgumentException("Неверная сумма корзины товаров");
        }
        double result = sumBasket;
        result -= result * discount / 100;
        return result;
    }
}
