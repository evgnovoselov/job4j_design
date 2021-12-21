package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Генерация отчета в виде html.
 */
public class ReportEngineHtmlView implements Report {
    private Store store;

    public ReportEngineHtmlView(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!doctype html><html lang=\"ru\"><head><meta charset=\"utf-8\"><title>Report</title></head>")
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
                .append("<tbody>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>")
                    .append("<td>")
                    .append(employee.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getHired())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getFired())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getSalary())
                    .append("</td>")
                    .append("</tr>");
        }
        text.append("</tbody>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        return text.toString();
    }
}
