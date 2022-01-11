package com.thana.api.utils.sugarapi;

public class TimeUtils {

    // Thanks to SteveKunGLibs for code and utilities
    public static String getRelativeTime(long timeDiff) {
        timeDiff /= 1000L;
        long current = System.currentTimeMillis() / 1000L;
        long timeElapsed = current - timeDiff;
        if (timeElapsed <= 60L) {
            return timeElapsed <= 0L ? "just now" : convertCorrectTime((int)timeElapsed, "second", false);
        } else {
            int minutes = Math.round((float)(timeElapsed / 60L));
            if (minutes <= 60) {
                return convertCorrectTime(minutes, "minute", false);
            } else {
                int hours = Math.round((float)(timeElapsed / 3600L));
                if (hours <= 24) {
                    return convertCorrectTime(hours, "hour", true);
                } else {
                    int days = Math.round((float)(timeElapsed / 86400L));
                    if (days <= 7) {
                        return convertCorrectTime(days, "day", false);
                    } else {
                        int weeks = Math.round((float)(timeElapsed / 604800L));
                        if (weeks <= 4) {
                            return convertCorrectTime(weeks, "week", false);
                        } else {
                            int months = Math.round((float)(timeElapsed / 2600640L));
                            if (months <= 12) {
                                return convertCorrectTime(months, "month", false);
                            } else {
                                int years = Math.round((float)(timeElapsed / 31207680L));
                                return convertCorrectTime(years, "year", false);
                            }
                        }
                    }
                }
            }
        }
    }

    private static String convertCorrectTime(int time, String text, boolean an) {
        return (time == 1 ? (an ? "an" : "a") : time) + " " + text + (time == 1 ? "" : "s") + " ago";
    }
}
