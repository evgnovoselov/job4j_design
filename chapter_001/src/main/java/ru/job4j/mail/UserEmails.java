package ru.job4j.mail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
                userPrevious = emailsUser.get(email);
                if (userPrevious != null) {
                    break;
                }
            }
            if (userPrevious == null) {
                result.put(userEmail.getKey(), new HashSet<>(userEmail.getValue()));
                for (var email : userEmail.getValue()) {
                    emailsUser.put(email, userEmail.getKey());
                }
            } else {
                var emailsResult = result.get(userPrevious);
                for (var email : userEmail.getValue()) {
                    emailsUser.putIfAbsent(email, userPrevious);
                    emailsResult.add(email);
                }
                result.put(userPrevious, emailsResult);
            }
        }
        return result;
    }
}
