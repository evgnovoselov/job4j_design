package ru.job4j.ood.dip.bad3;

/**
 * Простенький класс кнопки который нарушает принципа инверсии зависимости. Кнопка не должна зависить от реализации
 * кнопки, по хорошему должен быть сервис устройства, который будет реагировать на нажатие кнопки.
 */
public class Button {
    private Lamp lamp = new Lamp();

    public void press() {
        lamp.setWork(!lamp.isWork());
    }
}
