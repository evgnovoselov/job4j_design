package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
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
        StringBuilder expectedFormat = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        expectedFormat.append("<employees>\n");
        expectedFormat.append("    <employee>\n");
        expectedFormat.append("        <fired>%1$s</fired>\n");
        expectedFormat.append("        <hired>%1$s</hired>\n");
        expectedFormat.append("        <name>Evgeny</name>\n");
        expectedFormat.append("        <salary>700.0</salary>\n");
        expectedFormat.append("    </employee>\n");
        expectedFormat.append("    <employee>\n");
        expectedFormat.append("        <fired>%1$s</fired>\n");
        expectedFormat.append("        <hired>%1$s</hired>\n");
        expectedFormat.append("        <name>Ivan</name>\n");
        expectedFormat.append("        <salary>300.0</salary>\n");
        expectedFormat.append("    </employee>\n");
        expectedFormat.append("    <employee>\n");
        expectedFormat.append("        <fired>%1$s</fired>\n");
        expectedFormat.append("        <hired>%1$s</hired>\n");
        expectedFormat.append("        <name>Julia</name>\n");
        expectedFormat.append("        <salary>400.0</salary>\n");
        expectedFormat.append("    </employee>\n");
        expectedFormat.append("</employees>\n");
        String expected = String.format(expectedFormat.toString(), DatatypeConverter.printDateTime(now));
        assertEquals(expected, engine.generate(employee -> true));
    }
}