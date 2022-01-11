package ru.job4j.ood.lsp.bad1;

public class Manager extends Employee {
    public Manager(int salary) {
        super(salary);
    }

    @Override
    public void changeSalary(int money) {
        if (money < 0 && salary + money < 0) {
            throw new IllegalArgumentException("Зарплату нельзя сделать меньше 0");
        }
        if (salary + money > 150) {
            throw new IllegalArgumentException("Зарплата менеджера станет слишком большой");
        }
        salary += money;
    }
}
