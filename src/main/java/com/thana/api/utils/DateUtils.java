package com.thana.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMAT_SIMPLE = new SimpleDateFormat("dd/MM/yy");

    public static String suggestYear(String date) throws ParseException {
        return DATE_FORMAT.format(DATE_FORMAT_SIMPLE.parse(date));
    }
}
