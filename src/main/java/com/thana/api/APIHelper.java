package com.thana.api;

import com.thana.api.utils.DateUtils;
import com.thana.api.utils.TimeUtils;

import java.text.ParseException;

public class APIHelper {

    public static String getRelatedDate(String date) throws ParseException {
        long time = DateUtils.DATE_FORMAT.parse(date).getTime();
        return TimeUtils.getRelativeTime(time);
    }
}
