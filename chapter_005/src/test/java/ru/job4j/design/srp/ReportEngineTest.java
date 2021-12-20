package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

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
        Report engine = new ReportEngineHtmlView(store);
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

    @Test
    public void whenGeneratedReportChangeSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 123.45);
        store.add(worker);
        Report engine = new ReportEngineChangeSalary(store, salary -> String.format("%.2f руб.", salary));
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(String.format("%.2f руб.", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(employee -> true), expect.toString());
    }

    @Test
    public void whenGeneratedReportOrderBySalaryDescAndWithoutHiredFired() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee[] workers = new Employee[]{
                new Employee("Ivan", now, now, 100),
                new Employee("Petr", now, now, 300),
                new Employee("Alex", now, now, 400),
                new Employee("Oleg", now, now, 200),
        };
        Arrays.stream(workers).forEach(store::add);
        Report engine = new ReportEngineWithComparatorWithoutHiredFired(store, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Double.compare(o1.getSalary(), o2.getSalary());
            }
        }.reversed());
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workers[2].getName()).append(";")
                .append(workers[2].getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workers[1].getName()).append(";")
                .append(workers[1].getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workers[3].getName()).append(";")
                .append(workers[3].getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workers[0].getName()).append(";")
                .append(workers[0].getSalary()).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(employee -> true), expect.toString());
    }
}