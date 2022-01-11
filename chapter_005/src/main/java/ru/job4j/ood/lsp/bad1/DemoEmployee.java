package ru.job4j.ood.lsp.bad1;

/**
 * Неправильная реализация LSP.
 * В данном примере нарушается Предусловие - они усилины в подклассе, изменение максимальной зарплаты
 * менеджерам ограничено 150, когда у базовых такого ограничения нет.
 * Решение: Выделить интерфейс сотрудников или абстрагировать базовый класс для реализации у наследников и предоставить
 * возможность программно проверить максимальную зарплату.
 */
public class DemoEmployee {
    public static void main(String[] args) {
        Employee director = new Employee(100);
        changeSalary(director);
        Employee manager = new Manager(100);
        changeSalary(manager);
    }

    public static void changeSalary(Employee employee) {
        employee.changeSalary(150);
        System.out.println(employee.getSalary());
    }
}
