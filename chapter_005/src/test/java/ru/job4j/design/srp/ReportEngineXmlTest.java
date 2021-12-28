package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Тестовый класс генерации отчета в Xml.
 */
public class ReportEngineXmlTest {
    /**
     * Тестирование результата генерации отчета.
     */
    @Test
    public void whenGenerateXmlReportThenValidReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Evgeny", now, now, 700));
        store.add(new Employee("Ivan", now, now, 300));
        store.add(new Employee("Julia", now, now, 400));
        Report engine = new ReportEngineXml(store);
        assertEquals("TODO", engine.generate(employee -> true));
    }
}