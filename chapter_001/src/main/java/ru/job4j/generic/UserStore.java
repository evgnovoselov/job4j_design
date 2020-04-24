package ru.job4j.generic;

/**
 * Хранилаще пользователей.
 *
 * @author Evgeny Novoselov
 */
public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    /**
     * Добавление элемента в хранилише.
     *
     * @param model Элемент.
     */
    @Override
    public void add(User model) {
        store.add(model);
    }

    /**
     * Замена элемента в хранилище.
     *
     * @param id    Уникальный идентификатор.
     * @param model Элемент в хранилище.
     * @return Результат замены.
     */
    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    /**
     * Удаление элемента из хранилища по уникальному идентификатору.
     *
     * @param id Уникальный идентификатор.
     * @return Результат удаления.
     */
    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    /**
     * Поиск элемента в хранилище по уникальному идентификатору.
     *
     * @param id Уникальный идентификатор.
     * @return Найденный элемент.
     */
    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
