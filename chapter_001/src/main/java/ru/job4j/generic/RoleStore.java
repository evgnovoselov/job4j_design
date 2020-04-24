package ru.job4j.generic;

/**
 * Хранилище ролей.
 *
 * @author Evgeny Novoselov
 */
public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    /**
     * Добавление элемента в хранилише.
     *
     * @param model Элемент.
     */
    @Override
    public void add(Role model) {

    }

    /**
     * Замена элемента в хранилище.
     *
     * @param id    Уникальный идентификатор.
     * @param model Элемент в хранилище.
     * @return Результат замены.
     */
    @Override
    public boolean replace(String id, Role model) {
        return false;
    }

    /**
     * Удаление элемента из хранилища по уникальному идентификатору.
     *
     * @param id Уникальный идентификатор.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return false;
    }

    /**
     * Поиск элемента в хранилище по уникальному идентификатору.
     *
     * @param id Уникальный идентификатор.
     * @return Найденный элемент.
     */
    @Override
    public Role findById(String id) {
        return null;
    }
}
