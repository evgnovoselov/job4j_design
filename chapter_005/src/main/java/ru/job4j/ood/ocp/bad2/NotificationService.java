package ru.job4j.ood.ocp.bad2;

import java.util.List;

/**
 * Нарушение принципа открытости-закрытости тем, что при добавлении нового типа сервиса,
 * придется добавлять реализацию отправки уведомления.
 */
public class NotificationService {
    public void sendNotifications(List<Notification> notifications) {
        notifications.forEach(notification -> {
            if (notification.getServiceType().equals(ServiceType.PC)) {
                System.out.printf("На PC: %s%n", notification.getMessage());
            }
            if (notification.getServiceType().equals(ServiceType.MAIL)) {
                System.out.printf("На Mail: %s%n", notification.getMessage());
            }
            if (notification.getServiceType().equals(ServiceType.TELEGRAM)) {
                System.out.printf("На Telegram: %s%n", notification.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        List<Notification> notifications = List.of(
                new Notification(ServiceType.PC, "Уведомление на Компьютер"),
                new Notification(ServiceType.MAIL, "Уведомление на Почту"),
                new Notification(ServiceType.TELEGRAM, "Уведомление на Телеграмм")
        );
        service.sendNotifications(notifications);
    }
}
