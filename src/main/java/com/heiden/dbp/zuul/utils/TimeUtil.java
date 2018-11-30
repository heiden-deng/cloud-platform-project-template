package com.heiden.dbp.zuul.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtil.class);
    /**
     * 一毫秒
     */
    public static final long ONE_MILLSECOND = 1L;
    /**
     * 一秒的毫秒数
     */
    public static final long ONE_SECOND = ONE_MILLSECOND * 1000;
    /**
     * 一分的毫秒数
     */
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    /**
     * 一时的毫秒数
     */
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    /**
     * 一天的毫秒数
     */
    public static final long ONE_DAY = ONE_HOUR * 24;

    /**
     * 毫秒只转成字符串的天时分秒（如:1天1小时1分钟10秒）
     *
     * @param ms 毫秒值（long）
     * @return String
     */
    public static String mstoString(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分钟");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        // if(milliSecond > 0) {
        // sb.append(milliSecond+"毫秒");
        // }
        return sb.toString();

    }

    /**
     * 字符串日期转成毫秒值 yyyy-MM-dd HH:mm:dd
     *
     * @param da
     * @return
     */
    public static Long stringToLong(String da) {
        if (StringUtils.isEmpty(da)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long ts = 0;
        try {
            Date date = simpleDateFormat.parse(da);
            ts = date.getTime();
        } catch (ParseException e) {
            LOGGER.error("日期格式解析异常.....", e);
            throw new RuntimeException("日期格式解析异常.....");
        }
        return ts;

    }


    /**
     * 字符串日期转成毫秒值 yyyy-MM-dd
     *
     * @param da
     * @return
     */
    public static Long stringToLong2(String da) {
        if (StringUtils.isEmpty(da)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long ts = 0;
        try {
            Date date = simpleDateFormat.parse(da);
            ts = date.getTime();
        } catch (ParseException e) {
            LOGGER.error("日期格式解析异常.....", e);
            throw new RuntimeException("日期格式解析异常.....");
        }
        return ts;

    }

    /**
     * 将日期转成毫秒
     *
     * @param da
     * @return
     */
    public static String longToString(String da) {
        if (StringUtils.isEmpty(da)) {
            return null;
        }

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long lt = new Long(da);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            LOGGER.error("日期格式解析异常.....", e);
            throw new RuntimeException("日期格式解析异常.....");
        }


        return res;
    }

    /**
     * 将毫秒转成日期
     *
     * @param da
     * @return
     */
    public static String longToString(Long da) {

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = new Date(da);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            LOGGER.error("日期格式解析异常.....", e);
            throw new RuntimeException("日期格式解析异常.....");
        }


        return res;
    }

    /**
     * 获取当前时间到0点的秒值
     *
     * @return
     */
    public static long getTomorrowZeroSeconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }


}
