package ru.job4j.ood.lsp.bad2;

/**
 * Демонстрация нарущения LSP, здесь нарушено Постусловие, они не могут быть ослабены в подклассе.
 * Дисконт магазин продает товары и делает скидку только на карзину от определенной суммы,
 * в нем присутствует соответствующая проверка при продаже и выставлении скидки, которую игнорирует Свободный магазин.
 * Решение, использовать интерфейс Store.
 */
public class DemoStore {
    public static void main(String[] args) {
        int sumBasketProduct = 5000;
        DiscountStore store = new DiscountStore(10000, 10);
//        System.out.println(store.sale(sumBasketProduct)); error
        DiscountStore libreStore = new LibreStore(10000, 5);
        System.out.println(libreStore.sale(sumBasketProduct));
    }
}
