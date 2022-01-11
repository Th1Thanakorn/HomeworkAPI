package com.thana.api.utils.sugarapi;

public class StringUtils {

    public static String concatenate(String[] array) {
        String base = "";
        for (String str : array) {
            base = base.concat(str) + " ";
        }
        return base.trim();
    }
}
