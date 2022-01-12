package ru.job4j.ood.lsp.bad1;

public class Employee {
    protected int salary;

    public Employee(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не может быть меньше 0");
        }
        this.salary = salary;
    }

    public void changeSalary(int money) {
        if (money < 0 && salary + money < 0) {
            throw new IllegalArgumentException("Зарплату нельзя сделать меньше 0");
        }
        salary += money;
    }

    public int getSalary() {
        return salary;
    }
}
