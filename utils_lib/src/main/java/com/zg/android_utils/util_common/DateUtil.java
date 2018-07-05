package com.zg.android_utils.util_common;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static long getDiffSecond(Date time1, Date time2) {
        return ((time2.getTime() - time1.getTime()) / 1000);
    }

    public static int getHour(long seconds) {
        return (int) (seconds / 60 / 60);
    }

    public static int getMinute(long seconds) {
        return (int) (seconds % 3600 / 60);
    }

    public static int getSecond(long seconds) {
        return (int) (seconds % 3600 % 60);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat sf = null;
        if (time / 1000 > getZeroTimes()) {
            sf = new SimpleDateFormat("HH:mm");
            return sf.format(date);
        } else if (time > getYearTimes()) {
            sf = new SimpleDateFormat("MM-dd HH:mm");
            return sf.format(date);
        } else {
            sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sf.format(date);
        }
    }

    // 获得当天0点时间
    public static long getZeroTimes() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    // 获取当年的时间搓
    public static long getYearTimes() {
        Date date = new Date();
        int year = date.getYear();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date mYear = sf.parse((year + 1900) + "-01-01");
            return mYear.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addWeek(Date date, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, week);
        return calendar.getTime();
    }

    public static Date addDay(int year, int week, int day) {
        Calendar c = getFirstDayOfWeek(year, week);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.YEAR);
        return i;
    }

    public static int getYear() {
        return getYear(new Date());
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.MONTH);
        return i + 1;
    }

    public static int getMonth() {
        return getMonth(new Date());
    }

    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        return i;
    }

    public static int getWeek() {
        return getWeek(new Date());
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDay() {
        return getDay(new Date());
    }

    public static Date getFirstDayOfYear() {
        return getFirstDayOfYear(new Date());
    }

    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    public static Date getFirstDayOfMonth() {
        return getFirstDayOfMonth(new Date());
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getFirstDayOfThreeMonth() {
        return getFirstDayOfThreeMonth(new Date());
    }

    public static Date getFirstDayOfThreeMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month > 3) {
            calendar.set(Calendar.MONTH, month - 3);
        } else {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            calendar.set(Calendar.MONTH, month - month % 3);
        }

        return calendar.getTime();
    }

    public static Date getLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMaximum);
        return calendar.getTime();
    }

    public static Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        return calendar.getTime();
    }

    /**
     * 设置日期
     *
     * @param date
     * @param year
     * @param month 月份从0开始
     * @param day
     * @return
     */
    public static Date setDate(Date date, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date setYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Date setTime(Date date, int hour, int minute, int mils) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (hour >= 0) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute >= 0) {
            calendar.set(Calendar.MINUTE, minute);
        }
        if (mils >= 0) {
            calendar.set(Calendar.MILLISECOND, mils);
        }
        return calendar.getTime();
    }

    /**
     * 获取日期，时间将设为0
     *
     * @param date
     * @return
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayOfWeek() {
        return getFirstDayOfWeek(new Date());
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getFirstDayOfWeek(c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR)).getTime();
    }

    /**
     * @return
     */
    public static Calendar getFirstDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK);
        int day_of_month = c.get(Calendar.DAY_OF_MONTH);
        if (day_of_week == 1) {
            c.set(Calendar.DATE, day_of_month);
        } else if (day_of_week == 2) {
            c.set(Calendar.DATE, day_of_month - 1);
        } else if (day_of_week == 3) {
            c.set(Calendar.DATE, day_of_month - 2);
        } else if (day_of_week == 4) {
            c.set(Calendar.DATE, day_of_month - 3);
        } else if (day_of_week == 5) {
            c.set(Calendar.DATE, day_of_month - 4);
        } else if (day_of_week == 6) {
            c.set(Calendar.DATE, day_of_month - 5);
        } else if (day_of_week == 7) {
            c.set(Calendar.DATE, day_of_month - 6);
        }
        return c;
    }

    public static String date2String(Date date, String par) {
        return DateFormat.format(par, date).toString();
    }

    public static Date string2Date(String date, String par) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(par);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(String date, String sourcePar, String desPar) {
        Date string2Date = string2Date(date, sourcePar);
        return date2String(string2Date, desPar);
    }

    public static String format(Date date, String format) {
        if (date == null || TextUtils.isEmpty(format)) {
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取一年中第几周
     *
     * @return
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /**
     * 获取一年中第几周
     *
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtil.getYear(date1) == DateUtil.getYear(date2) && DateUtil.getMonth(date1) == DateUtil.getMonth(date2)
                && DateUtil.getDay(date1) == DateUtil.getDay(date2);
    }

    public static int compareDate(Date date1, Date date2) {
        date1 = getDate(date1);
        date2 = getDate(date2);
        return date1.compareTo(date2);
    }

    public static Calendar today() {
        Calendar today = Calendar.getInstance();
        return today;
    }

    public static boolean isInList(Calendar date, List<String> list) {
        for (String dateStr : list) {
            Calendar item = Calendar.getInstance();
            item.setTime(new Date(Long.valueOf(dateStr)));

            int year1 = date.get(Calendar.YEAR);
            int year2 = item.get(Calendar.YEAR);

            if (year1 != year2) {
                continue;
            }
            int month1 = date.get(Calendar.MONTH);
            int month2 = item.get(Calendar.MONTH);
            if (month1 != month2) {
                continue;
            }
            int day1 = date.get(Calendar.DAY_OF_MONTH);
            int day2 = item.get(Calendar.DAY_OF_MONTH);
            if (day1 == day2) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return (days <= 0 ? "" : days + "天") + (hours <= 0 ? "" : hours + "小时") + (minutes <= 0 ? "" : minutes + "分") + seconds + "秒";
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuringInMinites(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        return (days <= 0 ? "" : days + "天") + (hours <= 0 ? "" : hours + "小时") + (minutes <= 0 ? "" : minutes + "分");
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 00:00秒 后的格式
     */
    public static String formatDuringInSeconds(long mss) {
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return (hours <= 0 ? "" : (hours < 10 ? "0" + hours + ":" : hours + ":")) + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds + "");
    }

    public static String getCurrentTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 先把Date类型的日期转换为"yyyy-MM-dd"格式的字符串，再把转换成"yyyy-MM-dd"的字符串
     * 和"HH:mm"的字符串拼接起来转换成Date类型的日期值，最后返回该Date类型日期的Long值
     * @param date Date类型的日期值
     * @param time "HH:mm"格式的时间字符串
     * @return 拼接后的Long类型日期值
     */
    public static Long getTime(Date date, String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String day = formatter.format(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateDetail = null;
        try {
            dateDetail = format.parse(day + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            return dateDetail.getTime();
        }
    }

    /**
     * 按照“yyyy-MM-dd”字符串格式转换Date类型的日期（日期值只取到天，天后面的值省略不要）
     * @param date Date类型的日期值
     * @return 返回时间截取到“天”的Long类型日期
     */
    public static Long getLongTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String day = formatter.format(date);
        Date dateDetail = null;
        try {
            dateDetail = formatter.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return dateDetail.getTime();
        }
    }

    /**
     * 获取当前时间的称谓
     * 上午：00:00-10:00,中午：11:00-13:00, 下午：14:00-19:00, 晚上：20:00-24:00
     * @return 返回“上午”，“中午”，“下午”，“晚上”字符串
     */
    public static String getTimeName() {
        String name;
        int hour = Integer.valueOf(getCurrentTime("HH"));
        if (hour < 11) {
            name = "上午";
        } else if (hour <= 13) {
            name = "中午";
        } else if (hour <= 19) {
            name = "下午";
        } else {
            name = "晚上";
        }
        return name;
    }
}
