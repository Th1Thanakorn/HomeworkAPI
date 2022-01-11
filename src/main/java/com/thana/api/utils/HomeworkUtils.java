package com.thana.api.utils;

import com.google.common.collect.Lists;
import com.thana.api.HomeworkAPI;
import com.thana.api.date.DateOperators;
import com.thana.api.date.DaysOfWeek;
import com.thana.api.homeworks.ClassWork;
import com.thana.api.homeworks.Homeworks;
import com.thana.api.utils.sugarapi.ArrayHelper;
import com.thana.api.utils.sugarapi.ChatColor;
import com.thana.api.utils.sugarapi.DateUtils;
import com.thana.api.utils.sugarapi.StringUtils;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeworkUtils {

    public static final Pattern DATE_FORMAT_DECIMAL = Pattern.compile("(?<date>[0-9])\\/(?<month>[0-9])\\/(?<year>[0-9]{2})");
    public static final Pattern WEEK_DATE_FORMAT = Pattern.compile("(?<operator>:Pass?|pass?|) (?<day>\\w+)");

    public static void getHomeworkOnDate() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select type: Date or Name or All (d/n/a) ");
        String type = scanner.nextLine();
        String search = "";
        if (!type.equals("a")) {
            System.out.print("Query search: ");
            search = scanner.nextLine();
        }

        // Get date format
        switch (type) {
            case "d" -> {
                Matcher dateFormatDecimal = DATE_FORMAT_DECIMAL.matcher(search);
                Matcher weekDateFormat = WEEK_DATE_FORMAT.matcher(search);
                if (dateFormatDecimal.matches()) {
                    String[] elements = search.split("/");
                    if (elements.length == 3) {
                        search = DateUtils.suggestYear(search);
                    }
                } else if (weekDateFormat.matches()) {
                    String op = weekDateFormat.group("operator");
                    String day = weekDateFormat.group("day");
                    DateOperators operator = HomeworkUtils.deserializeOperator(op);
                    DaysOfWeek daysOfWeek = DaysOfWeek.getFromName(day);
                    Date lcDate = DateUtils.getNearlyDate(operator, daysOfWeek);
                    search = DateUtils.DATE_FORMAT.format(lcDate);
                    System.out.println(search);
                }
                Homeworks.HomeworkElement element = getHomeworks(search);
                if (element == null) {
                    System.out.println("Could not get related date!");
                    return;
                }
                System.out.println(Arrays.toString(element.getWorks()));
            }
            case "n" -> {
                List<ClassWork> searchedMap = new ArrayList<>();
                for (Date tempDate : HomeworkAPI.HOMEWORKS_HISTORY.keySet()) {
                    Homeworks.HomeworkElement element = HomeworkAPI.HOMEWORKS_HISTORY.get(tempDate);
                    String[] homeworks = element.getWorks();
                    for (String homework : homeworks) {
                        ClassWork classWork = getClassWorks(homework, tempDate);
                        searchedMap.add(classWork);
                    }
                }
                String finalSearch = search;
                searchedMap.removeIf((work) -> !work.subjectIsRelatedTo(finalSearch));
                HashMap<String, List<ClassWork>> groupedWorks = HomeworkUtils.sortAndGroup(searchedMap);
                System.out.println("\n");
                for (String subject : groupedWorks.keySet()) {
                    List<ClassWork> works = groupedWorks.get(subject);
                    System.out.println("Subject: " + subject);
                    for (ClassWork work : works) {
                        System.out.println(ChatColor.BLUE + DateUtils.DATE_FORMAT.format(work.getDate()) + ": " + ChatColor.WHITE + work.getWorks() + ChatColor.RESET);
                    }
                    System.out.println(HomeworkAPI.UNDERLINED);
                }
            }
            case "a" -> {
                for (Date tempDate : HomeworkAPI.HOMEWORKS_HISTORY.keySet()) {
                    String dateString = DateUtils.DATE_FORMAT.format(tempDate);
                    System.out.println(dateString);
                    System.out.println(Arrays.toString(HomeworkAPI.HOMEWORKS_HISTORY.get(tempDate).getWorks()));
                    System.out.println(HomeworkAPI.UNDERLINED);
                }
            }
        }
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

    public static ClassWork getClassWorks(String raw, Date date) {
        String[] scope = raw.split(" ");
        String[] skipped = ArrayHelper.skip(scope, 1);
        return new ClassWork(scope[0], StringUtils.concatenate(skipped), date);
    }

    public static HashMap<String, List<ClassWork>> sortAndGroup(List<ClassWork> works) {
        HashMap<String, List<ClassWork>> worksMap = new HashMap<>();
        for (ClassWork work : works) {
            if (!worksMap.containsKey(work.getSubject())) {
                worksMap.put(work.getSubject(), Lists.newArrayList(work));
            }
            else {
                List<ClassWork> tempList = worksMap.get(work.getSubject());
                tempList.add(work);
                tempList.sort(Comparator.comparingLong(o -> o.getDate().getTime()));
                worksMap.put(work.getSubject(), tempList);
            }
        }
        return worksMap;
    }
}
