package ru.job4j.menu;

import java.util.Iterator;

/**
 * Реализация принтера, который добавляет черточки и отступы для подразделов.
 */
public class LineMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()){
            Menu.MenuItemInfo next = iterator.next();
            System.out.println(next.getName());
        }
    }
}
