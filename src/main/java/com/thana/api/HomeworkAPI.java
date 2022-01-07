package com.thana.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thana.api.utils.ChatColor;
import com.thana.api.utils.DateUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeworkAPI {

    public static final String API_URL = "https://app.fakejson.com/q/1P7FQdFq?token=CVuc-35tnqfJ1eARz2OWYg";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Pattern DATE_FORMAT_DECIMAL = Pattern.compile("(?<date>[0-9])\\/(?<month>[0-9])\\/(?<year>[0-9]{2})");

    private static final HashMap<Date, Homeworks.HomeworkElement> HOMEWORKS_HISTORY = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {
        // Get Homeworks
        URL url = new URL(API_URL);
        String contents = IOUtils.toString(url, StandardCharsets.UTF_8);
        Homeworks homeworks = HomeworkAPI.GSON.fromJson(contents, Homeworks.class);
        String lastUpdated = homeworks.getLastUpdated();
        String apiDate = homeworks.getApiDate();
        String apiKey = homeworks.getApiKey();
        for (Homeworks.HomeworkElement element : homeworks.getHomeworks()) {
            Date date = DateUtils.DATE_FORMAT.parse(element.getDate());
            HOMEWORKS_HISTORY.put(date, element);
        }
        System.out.println(ChatColor.WHITE + "API Date: " + ChatColor.GREEN + APIHelper.getRelatedDate(apiDate) + ChatColor.RESET);
        System.out.println(ChatColor.WHITE + "Last Updated: " + ChatColor.GREEN + APIHelper.getRelatedDate(lastUpdated) + ChatColor.RESET);
        System.out.println(ChatColor.WHITE + "API Key: " + ChatColor.GREEN + apiKey + ChatColor.RESET);
        System.out.print("\n");

        // API Getter
        Scanner scanner = new Scanner(System.in);
        System.out.print("Get homework on date: ");
        String date = scanner.next();
        Matcher oneDecimalDateMatcher = DATE_FORMAT_DECIMAL.matcher(date);
        if (oneDecimalDateMatcher.matches()) {
            String[] elements = date.split("/");
            if (elements.length == 3) {
                date = DateUtils.suggestYear(date);
            }
        }
        Homeworks.HomeworkElement element = getHomeworks(date);
        if (element == null) {
            System.out.println("Could not get related date!");
            return;
        }
        System.out.println(Arrays.toString(element.getWorks()));
    }

    public static Homeworks.HomeworkElement getHomeworks(String day) throws ParseException {
        Date date = DateUtils.DATE_FORMAT.parse(day);
        for (Date homeworkDate : HOMEWORKS_HISTORY.keySet()) {
            if (date.getDate() == homeworkDate.getDate() && date.getMonth() == homeworkDate.getMonth() && date.getYear() == homeworkDate.getYear()) {
                return HOMEWORKS_HISTORY.get(homeworkDate);
            }
        }
        return null;
    }
}
