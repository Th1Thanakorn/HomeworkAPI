package com.thana.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thana.api.homeworks.Homeworks;
import com.thana.api.utils.sugarapi.ChatColor;
import com.thana.api.utils.sugarapi.DateUtils;
import com.thana.api.utils.HomeworkUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

public class HomeworkAPI {

    public static final String API_URL = "https://app.fakejson.com/q/1P7FQdFq?token=CVuc-35tnqfJ1eARz2OWYg";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final String UNDERLINED = "----------------------------------------------------------------------";

    public static final HashMap<Date, Homeworks.HomeworkElement> HOMEWORKS_HISTORY = new HashMap<>();

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
        HomeworkUtils.getHomeworkOnDate();
    }
}
