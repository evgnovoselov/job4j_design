package ru.job4j.menu;

public class TODOApp {
    private final Menu menu;
    private MenuPrinter printer;

    public TODOApp() {
        this(new SimpleMenu());
    }

    public TODOApp(Menu menu) {
        this(menu, new NumberConsolePrinter());
    }

    public TODOApp(Menu menu, MenuPrinter printer) {
        this.menu = menu;
        this.printer = printer;
    }

    public void setPrinter(MenuPrinter printer) {
        this.printer = printer;
    }

    public boolean add(String parentName, String childName) {
        return menu.add(parentName, childName, () -> {
        });
    }

    public void print() {
        printer.print(menu);
    }
}
