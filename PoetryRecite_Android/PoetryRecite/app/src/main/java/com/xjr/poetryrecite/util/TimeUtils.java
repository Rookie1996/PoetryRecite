package com.xjr.poetryrecite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtils() {
        throw new AssertionError();
    }


    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getNowTime() {
        return getTime(getCurrentTimeInLong(), DEFAULT_DATE_FORMAT);
    }

    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }


    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }


    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }


}

