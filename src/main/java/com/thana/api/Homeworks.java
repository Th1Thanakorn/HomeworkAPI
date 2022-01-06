package com.thana.api;

public class Homeworks {

    private HomeworkElement[] homeworks;

    public Homeworks(HomeworkElement[] homeworks) {
        this.homeworks = homeworks;
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
