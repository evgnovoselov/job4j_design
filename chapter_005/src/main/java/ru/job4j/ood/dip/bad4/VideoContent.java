package ru.job4j.ood.dip.bad4;

import java.util.HashMap;
import java.util.Map;

public class VideoContent {
    private final Map<Long, Video> video = new HashMap<>();

    public VideoContent() {
        video.put(1L, new Video(1L, "Человек-паук", new byte[0]));
        video.put(2L, new Video(2L, "Железный человек", new byte[0]));
        video.put(3L, new Video(3L, "Тор", new byte[0]));
        video.put(4L, new Video(4L, "Мстители", new byte[0]));
    }

    public Video findBy(Long id) {
        return video.get(id);
    }
}
