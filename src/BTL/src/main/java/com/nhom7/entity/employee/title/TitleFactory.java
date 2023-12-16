package com.nhom7.entity.employee.title;

import java.util.HashMap;
import java.util.Map;

public class TitleFactory {
    private static final TitleFactory instance = new TitleFactory();

    private final Map<String, Title> registeredTitles = new HashMap<>();

    private TitleFactory() {
    }

    public static TitleFactory instance() {
        return instance;
    }

    public void registerTitle(Title title) {
        this.registeredTitles.put(title.getTitle(), title);
    }

    public Title createTitle(String title) {
        return this.registeredTitles.get(title);
    }
}
