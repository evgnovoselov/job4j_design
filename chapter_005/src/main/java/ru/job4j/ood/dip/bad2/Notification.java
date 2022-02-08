package ru.job4j.ood.dip.bad2;

/**
 * Данный пример нарушает принцип инверсии зависимости тем, что зависит от конкретной реализации отправителя.
 * Его нужно выделить в отдельный интерфейс и класс уведомления должен зависить от него.
 */
public class Notification {
    private String text;
    private EmailSender sender = new EmailSender();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void send() {
        sender.send(text);
    }
}
