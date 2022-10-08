package com.aura.schedule;

public class Lesson {
    private final String time;
    private final String cabinet;
    private final String lesson;
    private final String teacher;

    public Lesson(String time, String cabinet, String lesson, String teacher) {
        this.time = time;
        this.cabinet = cabinet;
        this.lesson = lesson;
        this.teacher = teacher;
    }

    public String getTime() {
        return this.time;
    }

    public String getCabinet() {
        return cabinet;
    }

    public String getLesson() {
        return lesson;
    }

    public String getTeacher() {
        return teacher;
    }
}