package ru.job4j.menu;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TODOAppTest {
    @Test
    public void add() {
        TODOApp todo = new TODOApp();
        assertTrue(todo.add(TODOApp.ROOT, "Разработать сайт"));
        assertTrue(todo.add(TODOApp.ROOT, "Отправить платежки на оплату"));
        assertTrue(todo.add("Разработать сайт", "Раздел блог"));
        assertTrue(todo.add("Раздел блог", "Сверстать раздел блога"));
        assertTrue(todo.add("Раздел блог", "Запрограммировать блог"));
        assertTrue(todo.add("Сверстать раздел блога", "Сверстать шапку блога"));
        assertTrue(todo.add("Сверстать раздел блога", "Сверстать подвал блога"));
        assertTrue(todo.add("Разработать сайт", "Раздел продукты"));
        assertTrue(todo.add("Раздел продукты", "Сверстать раздел продуктов"));
        assertTrue(todo.add("Раздел продукты", "Сверстать карточку продукта"));
        assertFalse(todo.add("Такой задачи не существует", "Все равно попробуем вставить"));
    }

    @Test
    public void print() {
    }
}