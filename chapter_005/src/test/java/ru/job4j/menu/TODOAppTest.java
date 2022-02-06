package ru.job4j.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TODOAppTest {
    @Test
    public void whenAddItemsTODOThenTrue() {
        TODOApp todo = new TODOApp();
        assertTrue(todo.add(Menu.ROOT, "Разработать сайт"));
        assertTrue(todo.add(Menu.ROOT, "Отправить платежки на оплату"));
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
    public void whenPrintTODOThenDefaultPrint() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(mem));
        TODOApp todo = new TODOApp();
        todo.add(Menu.ROOT, "Разработать сайт");
        todo.add(Menu.ROOT, "Отправить платежки на оплату");
        todo.add("Разработать сайт", "Раздел блог");
        todo.add("Раздел блог", "Сверстать раздел блога");
        todo.add("Раздел блог", "Запрограммировать блог");
        todo.add("Сверстать раздел блога", "Сверстать шапку блога");
        todo.add("Сверстать раздел блога", "Сверстать подвал блога");
        todo.add("Разработать сайт", "Раздел продукты");
        todo.add("Раздел продукты", "Сверстать раздел продуктов");
        todo.add("Раздел продукты", "Сверстать карточку продукта");
        todo.print();
        String expected = "1. Разработать сайт" + System.lineSeparator() +
                "1.1. Раздел блог" + System.lineSeparator() +
                "1.1.1. Сверстать раздел блога" + System.lineSeparator() +
                "1.1.1.1. Сверстать шапку блога" + System.lineSeparator() +
                "1.1.1.2. Сверстать подвал блога" + System.lineSeparator() +
                "1.1.2. Запрограммировать блог" + System.lineSeparator() +
                "1.2. Раздел продукты" + System.lineSeparator() +
                "1.2.1. Сверстать раздел продуктов" + System.lineSeparator() +
                "1.2.2. Сверстать карточку продукта" + System.lineSeparator() +
                "2. Отправить платежки на оплату" + System.lineSeparator();
        assertEquals(expected, mem.toString());
        System.setOut(oldOut);
    }

    @Test
    public void whenLinePrintTODOThenLinePrint() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(mem));
        TODOApp todo = new TODOApp();
        todo.add(Menu.ROOT, "Разработать сайт");
        todo.add(Menu.ROOT, "Отправить платежки на оплату");
        todo.add("Разработать сайт", "Раздел блог");
        todo.add("Раздел блог", "Сверстать раздел блога");
        todo.add("Раздел блог", "Запрограммировать блог");
        todo.add("Сверстать раздел блога", "Сверстать шапку блога");
        todo.add("Сверстать раздел блога", "Сверстать подвал блога");
        todo.add("Разработать сайт", "Раздел продукты");
        todo.add("Раздел продукты", "Сверстать раздел продуктов");
        todo.add("Раздел продукты", "Сверстать карточку продукта");
        todo.setPrinter(new LineMenuConsolePrinter());
        todo.print();
        String expected = "Разработать сайт" + System.lineSeparator() +
                "---- Раздел блог" + System.lineSeparator() +
                "-------- Сверстать раздел блога" + System.lineSeparator() +
                "------------ Сверстать шапку блога" + System.lineSeparator() +
                "------------ Сверстать подвал блога" + System.lineSeparator() +
                "-------- Запрограммировать блог" + System.lineSeparator() +
                "---- Раздел продукты" + System.lineSeparator() +
                "-------- Сверстать раздел продуктов" + System.lineSeparator() +
                "-------- Сверстать карточку продукта" + System.lineSeparator() +
                "Отправить платежки на оплату" + System.lineSeparator();
        assertEquals(expected, mem.toString());
        System.setOut(oldOut);
    }
}