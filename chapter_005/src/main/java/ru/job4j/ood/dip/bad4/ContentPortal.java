package ru.job4j.ood.dip.bad4;

/**
 * Данный пример нарушает принцип инверсии зависимости, тем что привязан к единственному типу контента видео,
 * из-за данной реализации будет сложность отдавать например книги или музыку. Нужно в данному случае использовать
 * абстракцию контента.
 */
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
