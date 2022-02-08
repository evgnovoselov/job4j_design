package ru.job4j.ood.dip.bad3;

import java.util.HashMap;
import java.util.Map;

public class VideoContent {
    private Map<Long, String> video = new HashMap<>();

    public VideoContent() {
        video.put(1L, "Человек-паук");
        video.put(2L, "Человек-паук");
        video.put(3L, "Тор");
        video.put(4L, "Мстители");
    }

    public String findBy(Long id) {
        return video.get(id);
    }
}
