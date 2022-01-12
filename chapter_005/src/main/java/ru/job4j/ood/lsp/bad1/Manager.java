package ru.job4j.ood.lsp.bad1;

public class Manager extends Employee {
    public Manager(int salary) {
        super(salary);
        checkSalary(salary);
    }

    @Override
    public void changeSalary(int money) {
        if (money < 0 && salary + money < 0) {
            throw new IllegalArgumentException("Зарплату нельзя сделать меньше 0");
        }
        checkSalary(salary + money);
        salary += money;
    }

    private void checkSalary(int salary) {
        if (salary > 150) {
            throw new IllegalArgumentException("Зарплата менеджера станет слишком большой");
        }
    }
}
