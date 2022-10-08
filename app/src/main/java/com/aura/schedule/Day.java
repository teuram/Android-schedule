package com.aura.schedule;

import java.util.ArrayList;

public class Day {
    private final String dayOfWeek;
    private final String dayOfMonth;
    private final Group[] groups;

    public Day(String dayOfWeek, String dayOfMonth, Group[] groups) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.groups = groups;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }
    public String getDayOfMonth() {
        return this.dayOfMonth;
    }
    public Group[] getGroups() {
        return this.groups;
    }

}