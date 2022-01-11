package com.thana.api.utils;

import com.thana.api.utils.sugarapi.DateUtils;
import com.thana.api.utils.sugarapi.TimeUtils;

import java.text.ParseException;

public class APIHelper {

    public static String getRelatedDate(String date) throws ParseException {
        long time = DateUtils.DATE_FORMAT.parse(date).getTime();
        return TimeUtils.getRelativeTime(time);
    }
}
