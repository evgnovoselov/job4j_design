package ru.job4j.generic;

/**
 * Базовая модель объекта для хранения в контейнере.
 *
 * @author Evgeny Novoselov
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Вернуть уникальный идентификатор объекта.
     *
     * @return Уникальный идентификатор.
     */
    public String getId() {
        return id;
    }
}
