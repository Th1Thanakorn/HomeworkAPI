package com.thana.api.homeworks;

import com.google.gson.annotations.SerializedName;

public class Homeworks {

    private HomeworkElement[] homeworks;
    @SerializedName("api_date")
    private String apiDate;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("api_key")
    private String apiKey;

    public Homeworks(HomeworkElement[] homeworks, String apiDate, String lastUpdated) {
        this.homeworks = homeworks;
        this.apiDate = apiDate;
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getApiDate() {
        return apiDate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public HomeworkElement[] getHomeworks() {
        return homeworks;
    }

    public static class HomeworkElement {

        private String date;
        private String[] works;

        public HomeworkElement(String date, String[] works) {
            this.date = date;
            this.works = works;
        }

        public String getDate() {
            return date;
        }

        public String[] getWorks() {
            return works;
        }
    }
}
