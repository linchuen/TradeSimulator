package com.cooba.TradeSimulator.Util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String NORMAL_FORMAT = "yyyyMMdd";
    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public static String toFormatString(int year, int month, int day) {
        return LocalDate.of(year, month, day).format(DateTimeFormatter.ofPattern(NORMAL_FORMAT));
    }

    public static String toFormatString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(NORMAL_FORMAT));
    }

    public static String toFormatString(int year, int month, int day, String type) {
        return LocalDate.of(year, month, day).format(DateTimeFormatter.ofPattern(type));
    }

    public static LocalDate getDateByString(String str) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(NORMAL_FORMAT);
        if (str.length() == 6) return LocalDate.parse(str + "01", format);
        return LocalDate.parse(str, format);
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
