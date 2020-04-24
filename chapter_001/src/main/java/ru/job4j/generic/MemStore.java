package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Каркас универсального хранилища.
 *
 * @param <T> Тип хранимой модели наследовавшийся от базовой.
 * @author Evgeny Novoselov
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    /**
     * Добавление элемента в хранилише.
     *
     * @param model Элемент.
     */
    @Override
    public void add(T model) {
        mem.add(model);
    }

    /**
     * Замена элемента в хранилище.
     *
     * @param id    Уникальный идентификатор.
     * @param model Элемент в хранилище.
     * @return Результат замены.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        ListIterator<T> it = mem.listIterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.set(model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Удаление элемента из хранилища по уникальному идентификатору.
     *
     * @param id Уникальный идентификатор.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return mem.removeIf(el -> el.getId().equals(id));
    }

    /**
     * Поиск элемента в хранилище по уникальному идентификатору.
     *
     * @param id Уникальный идентификатор.
     * @return Найденный элемент.
     */
    @Override
    public T findById(String id) {
        return mem.stream().filter(el -> el.getId().equals(id)).findFirst().orElse(null);
    }
}
