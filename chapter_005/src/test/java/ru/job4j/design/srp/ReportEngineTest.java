package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Тест отчетов.
 */
public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(employee -> true), expect.toString());
    }

    @Test
    public void whenGeneratedHtmlReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportHtmlView(store);
        StringBuilder expected = new StringBuilder()
                .append("<!doctype html><html lang=\"ru\"><head><meta charset=\"utf-8\"><title>Report</title></head>")
                .append("<body>")
                .append("<table>")
                .append("<thead>")
                .append("<tr>")
                .append("<th>Name</th>")
                .append("<th>Hired</th>")
                .append("<th>Fired</th>")
                .append("<th>Salary</th>")
                .append("</tr>")
                .append("</thead>")
                .append("<tbody>")
                .append("<tr>")
                .append("<td>")
                .append(worker.getName())
                .append("</td>")
                .append("<td>")
                .append(worker.getHired())
                .append("</td>")
                .append("<td>")
                .append(worker.getFired())
                .append("</td>")
                .append("<td>")
                .append(worker.getSalary())
                .append("</td>")
                .append("</tr>")
                .append("</tbody>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        assertEquals(engine.generate(employee -> true), expected.toString());
    }
}