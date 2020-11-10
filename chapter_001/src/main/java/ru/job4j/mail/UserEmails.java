package ru.job4j.mail;

import java.util.*;

/**
 * Класс для работы с пользователями и их почтой.
 */
public class UserEmails {
    /**
     * Метод делает слияние пользователей с похожими адресами почтой.
     *
     * @param userEmails отображение пользователей с множеством почтой.
     * @return отображение пользователей с множеством почты после слияния пользователей.
     */
    public static Map<String, Set<String>> merge(Map<String, Set<String>> userEmails) {
        var result = new HashMap<String, Set<String>>();
        var emailsUser = new HashMap<String, String>();
        for (var userEmail : userEmails.entrySet()) {
            String userPrevious = null;
            for (var email : userEmail.getValue()) {
                if ((userPrevious = emailsUser.get(email)) != null) {
                    break;
                }
            }
            if (userPrevious == null) {
                for (var email : userEmail.getValue()) {
                    emailsUser.put(email, userEmail.getKey());
                }
                result.put(userEmail.getKey(), new HashSet<>(userEmail.getValue()));
            } else {
                Set<String> emails = new HashSet<>(userEmails.get(userPrevious));
                for (var email : userEmail.getValue()) {
                    emailsUser.putIfAbsent(email, userPrevious);
                    emails.add(email);
                }
                result.put(userPrevious, emails);
            }
        }
        return result;
    }
}
