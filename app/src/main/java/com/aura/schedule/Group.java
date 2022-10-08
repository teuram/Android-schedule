package com.aura.schedule;

import java.util.ArrayList;

public class Group {
    private final String group;
    private final Lesson[] lessons;

    public Group(String group, Lesson[] lessons) {
        this.group = group;
        this.lessons = lessons;
    }

    public String getGroup() {
        return this.group;
    }

    public Lesson[] getLessons() {
        return this.lessons;
    }
}
