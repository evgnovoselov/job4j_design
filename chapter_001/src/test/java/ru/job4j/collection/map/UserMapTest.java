package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Тестирование работы отображения.
 */
public class UserMapTest {
    @Test
    public void whenUserMapPrintThenSeeHas() {
        User first = new User("Name", new GregorianCalendar(2000, Calendar.OCTOBER, 7), 3);
        User second = new User("Name", new GregorianCalendar(2000, Calendar.OCTOBER, 7), 3);
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first user");
        map.put(second, "second user");
        System.out.println(map);
    }
}
