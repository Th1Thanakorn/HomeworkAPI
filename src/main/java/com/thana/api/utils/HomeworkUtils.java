package com.thana.api.utils;

import com.thana.api.HomeworkAPI;
import com.thana.api.Homeworks;
import com.thana.api.date.DateOperators;
import com.thana.api.date.DaysOfWeek;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeworkUtils {

    public static final Pattern DATE_FORMAT_DECIMAL = Pattern.compile("(?<date>[0-9])\\/(?<month>[0-9])\\/(?<year>[0-9]{2})");
    public static final Pattern WEEK_DATE_FORMAT = Pattern.compile("(?<operator>:Pass?|pass?|) (?<day>\\w+)");

    public static void getHomeworkOnDate() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Get homework on date: ");
        String date = scanner.nextLine();

        // Get date format
        Matcher dateFormatDecimal = DATE_FORMAT_DECIMAL.matcher(date);
        Matcher weekDateFormat = WEEK_DATE_FORMAT.matcher(date);
        if (dateFormatDecimal.matches()) {
            String[] elements = date.split("/");
            if (elements.length == 3) {
                date = DateUtils.suggestYear(date);
            }
        }
        else if (weekDateFormat.matches()) {
            String op = weekDateFormat.group("operator");
            String day = weekDateFormat.group("day");
            DateOperators operator = HomeworkUtils.deserializeOperator(op);
            DaysOfWeek daysOfWeek = DaysOfWeek.getFromName(day);
            Date lcDate = DateUtils.getNearlyDate(operator, daysOfWeek);
            date = DateUtils.DATE_FORMAT.format(lcDate);
            System.out.println(date);
        }

        // Get homeworks
        Homeworks.HomeworkElement element = getHomeworks(date);
        if (element == null) {
            System.out.println("Could not get related date!");
            return;
        }
        System.out.println(Arrays.toString(element.getWorks()));
    }

    public static Homeworks.HomeworkElement getHomeworks(String day) throws ParseException {
        Date date = DateUtils.DATE_FORMAT.parse(day);
        for (Date homeworkDate : HomeworkAPI.HOMEWORKS_HISTORY.keySet()) {
            if (date.getDate() == homeworkDate.getDate() && date.getMonth() == homeworkDate.getMonth() && date.getYear() == homeworkDate.getYear()) {
                return HomeworkAPI.HOMEWORKS_HISTORY.get(homeworkDate);
            }
        }
        return null;
    }

    public static DateOperators deserializeOperator(String name) {
        return DateOperators.getOperatorFromName(name);
    }
}
