package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование генерации отчетов в формате Json.
 */
public class ReportEngineJsonTest {
    @Test
    public void whenCreateReportJsonThenJsonFormatReport() {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar(2022, Calendar.JANUARY, 7, 11, 37, 15);
        String dateJson = "{\"year\":2022,\"month\":0,\"dayOfMonth\":7,\"hourOfDay\":11,\"minute\":37,\"second\":15}";
        store.add(new Employee("Evgeny", date, date, 700));
        store.add(new Employee("Ivan", date, date, 300));
        store.add(new Employee("Julia", date, date, 400));
        Report engine = new ReportEngineJson(store);
        StringBuilder expectedFormat = new StringBuilder();
        expectedFormat.append("[{");
        expectedFormat.append("\"name\":\"Evgeny\",");
        expectedFormat.append("\"hired\":%1$s,");
        expectedFormat.append("\"fired\":%1$s,");
        expectedFormat.append("\"salary\":700.0");
        expectedFormat.append("},{");
        expectedFormat.append("\"name\":\"Ivan\",");
        expectedFormat.append("\"hired\":%1$s,");
        expectedFormat.append("\"fired\":%1$s,");
        expectedFormat.append("\"salary\":300.0");
        expectedFormat.append("},{");
        expectedFormat.append("\"name\":\"Julia\",");
        expectedFormat.append("\"hired\":%1$s,");
        expectedFormat.append("\"fired\":%1$s,");
        expectedFormat.append("\"salary\":400.0");
        expectedFormat.append("}]");
        String expected = String.format(expectedFormat.toString(), dateJson);
        assertEquals(expected, engine.generate(employee -> true));
    }
}