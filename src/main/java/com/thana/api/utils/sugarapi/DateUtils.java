package com.thana.api.utils.sugarapi;

import com.thana.api.date.DateOperators;
import com.thana.api.date.DaysOfWeek;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtils {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMAT_SIMPLE = new SimpleDateFormat("dd/MM/yy");
    public static final ZoneId THAILAND_ZONE_ID = ZoneId.of("Asia/Bangkok");

    public static String suggestYear(String date) throws ParseException {
        return DATE_FORMAT.format(DATE_FORMAT_SIMPLE.parse(date));
    }

    public static Date getNearlyDate(DateOperators operators, DaysOfWeek daysOfWeek) {
        LocalDate localDate = LocalDate.now(THAILAND_ZONE_ID);
        if (operators == DateOperators.PASS) {
            return DateUtils.toDate(localDate.with(TemporalAdjusters.previous(daysOfWeek.toCalendarDay())));
        }
        return DateUtils.toDate(localDate);
    }

    private static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(THAILAND_ZONE_ID).toInstant());
    }
}
