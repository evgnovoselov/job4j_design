package ru.job4j.ood.isp.bad3;

public class Page implements Item {
    private String title;
    private String content;

    public Page(String title, String content) {
        this.title = title;
        this.content = content;
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
        throw new UnsupportedOperationException("Используется страница.");
    }

    @Override
    public void setPrice(int price) {
        throw new UnsupportedOperationException("Страница не содержит стоимость.");
    }
}
