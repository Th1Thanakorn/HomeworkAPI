package com.thana.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class HomeworkAPI {

    public static final String API_URL = "https://app.fakejson.com/q/1P7FQdFq?token=CVuc-35tnqfJ1eARz2OWYg";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final HashMap<Date, Homeworks.HomeworkElement> HOMEWORKS_HISTORY = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {
        // Get Homeworks
        URL url = new URL(API_URL);
        String contents = IOUtils.toString(url, StandardCharsets.UTF_8);
        Homeworks homeworks = HomeworkAPI.GSON.fromJson(contents, Homeworks.class);
        for (Homeworks.HomeworkElement element : homeworks.getHomeworks()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(element.getDate());
            HOMEWORKS_HISTORY.put(date, element);
        }

        // API Getter
        Scanner scanner = new Scanner(System.in);
        System.out.print("Get homework on date: ");
        String date = scanner.next();
        Homeworks.HomeworkElement element = getHomeworks(date);
        if (element == null) {
            System.out.println("Could not get related date!");
            return;
        }
        System.out.println(Arrays.toString(element.getWorks()));
    }

    public static Homeworks.HomeworkElement getHomeworks(String day) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(day);
        for (Date homeworkDate : HOMEWORKS_HISTORY.keySet()) {
            if (date.getDate() == homeworkDate.getDate()) {
                return HOMEWORKS_HISTORY.get(homeworkDate);
            }
        }
        return null;
    }
}
