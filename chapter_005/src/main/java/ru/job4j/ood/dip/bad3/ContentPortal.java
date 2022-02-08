package ru.job4j.ood.dip.bad3;

public class ContentPortal {
    private VideoContent contents = new VideoContent();
    private UserRepository users = new UserRepository();

    public void show(long userId, long contentId) {
        User user = users.findBy(userId);
        String content = contents.findBy(contentId);
        if (user == null) {
            throw new IllegalArgumentException("Error User");
        }
        if (content == null) {
            throw new IllegalArgumentException("Error Content");
        }
        System.out.println("Show content");
    }
}
