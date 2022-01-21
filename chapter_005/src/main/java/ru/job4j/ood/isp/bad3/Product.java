package ru.job4j.ood.isp.bad3;

public class Product implements Item {
    private String title;
    private String content;
    private int price;

    public Product(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }
}
