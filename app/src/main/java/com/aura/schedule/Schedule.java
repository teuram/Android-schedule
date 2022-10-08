package com.aura.schedule;

import java.util.ArrayList;
import java.util.Locale;

public class Schedule {
    private final Day[] days;

    public Schedule(Day[] days) {
        this.days = days;
    }

    public Day[] getDays() {
        return days;
    }

    public String[] getListGroups() {
        ArrayList<String> groups = new ArrayList<>();
        for(Group gr: this.getDays()[0].getGroups()) {
            groups.add(gr.getGroup());
        }
        return groups.toArray(new String[0]);
    }

    public String getGroup(String gr) {
        StringBuilder sb = new StringBuilder();
        for(Day day: days) {
            sb.append(day.getDayOfWeek());
            sb.append(" ");
            sb.append(day.getDayOfMonth());
            sb.append("\n\n");

            for(Group group: day.getGroups()) {
                if(group.getGroup().toLowerCase(Locale.ROOT)
                        .equalsIgnoreCase(gr.toLowerCase(Locale.ROOT))) {
                    sb.append("Группа: ");
                    sb.append(group.getGroup());
                    sb.append("\n");
                    for(Lesson lesson: group.getLessons()) {
                        if(!lesson.getLesson().equalsIgnoreCase("")) {
                            sb.append("Время: ");
                            sb.append(lesson.getTime());
                            sb.append("  кабинет: ");
                            sb.append(lesson.getCabinet());
                            sb.append("  переподователь: ");
                            sb.append(lesson.getTeacher());
                            sb.append("\n");
                            sb.append(lesson.getLesson());
                            sb.append("\n\n");
                        }
                    }
                }
            }

        }
        return sb.toString();
    }
}
