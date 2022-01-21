package ru.job4j.ood.isp.bad3;

/**
 * Данный интерфейс нарушает принцип разделения иинтерфейсов (ISP) так как он описывает работу товара и страницы,
 * чтобы он удволитворял принципа ISP нужно их разделить и интерфейс продукта расширил интерфейс итема.
 */
public interface Item {
    String getTitle();

    void setTitle(String title);

    String getContent();

    void setContent(String content);

    int getPrice();

    void setPrice(int price);
}
