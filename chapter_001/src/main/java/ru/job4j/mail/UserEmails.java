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
        var emailsQueue = new LinkedList<String>();
        for (var userEmail : userEmails.entrySet()) {
            String userPrevious = null;
            for (var email : userEmail.getValue()) {
                if (userPrevious == null) {
                    userPrevious = emailsUser.get(email);
                }
                emailsQueue.offer(email);
            }
            if (userPrevious == null) {
                result.put(userEmail.getKey(), new HashSet<>(userEmail.getValue()));
                while (!emailsQueue.isEmpty()) {
                    emailsUser.put(emailsQueue.poll(), userEmail.getKey());
                }
            } else {
                var emailsResult = result.get(userPrevious);
                while (!emailsQueue.isEmpty()) {
                    var email = emailsQueue.poll();
                    emailsUser.putIfAbsent(email, userPrevious);
                    emailsResult.add(email);
                }
                result.put(userPrevious, emailsResult);
            }
        }
        return result;
    }
}
