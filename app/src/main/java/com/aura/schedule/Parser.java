package com.aura.schedule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Parser {

    private static final String LINK = "https://nntc.nnov.ru/sites/default/files/sched/schedule_2.html";

    public static Schedule getSchedule() throws IOException {
        Document document = Jsoup.connect(LINK).get();
        Element el = document.selectFirst("tbody");

        return new Schedule(parse(el));
    }

    private static Day[] parse(Element table) {

        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();

        String dayOfWeak = null;
        String dayOfMonth = null;

        boolean headOfDay = false;
        boolean isFirstCycle = true;

        for (Element e : table.children()) {

            // определение дня недели
            if(headOfDay) {
                headOfDay = false;
                continue;
            }
            if(e.children().size() == 1) {
                String str = e.children().text().toLowerCase(Locale.ROOT);
                if(str.contains("(") && str.contains(")")) {
                    if(isFirstCycle) {
                        isFirstCycle = false;
                    } else {
                        days.add(new Day(dayOfWeak, dayOfMonth, groups.toArray(new Group[0])));
                        groups.clear();
                    }
                    dayOfWeak = e.text().split("[(]")[1].split("[)]")[0]
                            .toLowerCase(Locale.ROOT);

                    String[] buffer = e.text().split(" ");
                    dayOfMonth = String.format("%s %s", buffer[3], buffer[4])
                            .toLowerCase(Locale.ROOT);

                    headOfDay = true;

                    continue;
                }
            } else {
                continue;
            }


            // определение группы и пар

            Element[] groupElements = e.children().toArray(new Element[0]);
            if(groupElements.length == 1) {
                continue;
            }
            if(groupElements.length == 1 + 3 * 6) {

            } else if(groupElements.length == 1 + 3 * 7) {

            } else {
                continue;
            }
            ArrayList<Lesson> lessons = new ArrayList<>();
            String groupStr = groupElements[0].text();

            for(int i = 1; i < groupElements.length; i += 3) {
                //String[] buffer = groupElements[i].text()
                //        .replace(" ", "").split("-");
                String[] buffer;

                //String time = String.format("%s:%s - %s:%s",
                //        buffer[0], buffer[1], buffer[2], buffer[3]);
                String time = groupElements[i].text();//.replace(" ", "");

                String cabinet = groupElements[i + 1].text();

                buffer = groupElements[i + 2].text().split("/");
                String lesson = buffer[0].trim();
                String teacher;
                if(buffer.length == 1) {
                    teacher = "";
                } else {
                    teacher = buffer[1].trim();
                }

                lessons.add(new Lesson(time, cabinet, lesson, teacher));
            }
            groups.add(new Group(groupStr, lessons.toArray(new Lesson[0])));

        }
        days.add(new Day(dayOfWeak, dayOfMonth, groups.toArray(new Group[0])));

        return days.toArray(new Day[0]);
    }
}
