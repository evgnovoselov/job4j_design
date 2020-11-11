package ru.job4j.mail;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тестирование хранения пользователей и их почт.
 */
public class UserEmailsTest {
    /**
     * При добавлении пользователей с одинаковой почтой должно произойти слияние пользователей.
     */
    @Test
    public void whenAddUsersAndSameEmailsThenMergeUsers() {
        Map<String, Set<String>> userEmails = new LinkedHashMap<>();
        userEmails.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        userEmails.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        userEmails.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        userEmails.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        userEmails.put("user5", Set.of("xyz@pisem.net"));
        Map<String, Set<String>> expected = UserEmails.merge(userEmails);
        assertThat(expected, is(Map.of(
                "user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
                "user3", Set.of("xyz@pisem.net", "vasya@pupkin.com")
        )));
    }
}
