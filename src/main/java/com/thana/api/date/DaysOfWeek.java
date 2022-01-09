package com.thana.api.date;

import java.time.DayOfWeek;
import java.util.Calendar;

public enum DaysOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String name;

    DaysOfWeek(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DaysOfWeek getFromName(String name) {
        for (DaysOfWeek day : values()) {
            if (name.equalsIgnoreCase(day.getName())) {
                return day;
            }
        }
        return null;
    }

    public DayOfWeek toCalendarDay() {
        return switch (this) {
            case MONDAY -> DayOfWeek.MONDAY;
            case TUESDAY -> DayOfWeek.TUESDAY;
            case WEDNESDAY -> DayOfWeek.WEDNESDAY;
            case THURSDAY -> DayOfWeek.THURSDAY;
            case FRIDAY -> DayOfWeek.FRIDAY;
            case SATURDAY -> DayOfWeek.SATURDAY;
            case SUNDAY -> DayOfWeek.SUNDAY;
        };
    }
}
