package ru.job4j.ood.dip.bad4;

public class ContentPortal {
    private final VideoContent contents = new VideoContent();
    private final UserRepository users = new UserRepository();

    public byte[] show(long userId, long contentId) {
        User user = users.findBy(userId);
        Video content = contents.findBy(contentId);
        if (user == null) {
            throw new IllegalArgumentException("Error User");
        }
        if (content == null) {
            throw new IllegalArgumentException("Error Content");
        }
        return content.getData();
    }
}
