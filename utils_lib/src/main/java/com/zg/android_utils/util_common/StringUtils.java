package com.zg.android_utils.util_common;

import android.util.Base64;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    /**
     * 格式化 float 四舍五入
     *
     * @param d
     * @param scale
     * @return
     */
    public static final String DEVICE_ID_SPLITTER = ";";

    public static String format(float d, int scale) {
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }

    public static String format(double d, int scale) {
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }

    /**
     * 保留三位小数
     *
     * @param d
     * @return
     */
    public static String mFormat3(double d) {
        DecimalFormat dFormat = new DecimalFormat();
        dFormat.applyPattern("#0.000");
        return dFormat.format(d * 1000 / 1000.0);
    }

    /**
     * 保留二位小数
     *
     * @param d
     * @return
     */
    public static String mFormat2(double d) {
        DecimalFormat dFormat = new DecimalFormat();
        dFormat.applyPattern("#0.00");
        return dFormat.format(d * 100 / 100.0);
    }

    /**
     * 保留一位小数
     *
     * @param d
     * @return
     */
    public static String mFormat1(double d) {
        DecimalFormat dFormat = new DecimalFormat();
        dFormat.applyPattern("#0.0");
        return dFormat.format(d * 10 / 10.0);
    }

    public static String getBASE64(String s) {
        if (s == null)
            return null;
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0 || s.toLowerCase().equals("null");

    }

    /**
     * 将字符数组转换为字符串
     *
     * @param strings  字符数组
     * @param splitter 用于间隔字符的分隔符，默认分隔符是";"
     * @return
     */

    public static String toString(String[] strings, String splitter) {
        if (strings == null || strings.length <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        int length = strings.length;
        for (String s : strings) {
            stringBuffer.append(s);
            if (i < length - 1) {
                stringBuffer.append(splitter);
            }
            i++;
        }
        return stringBuffer.toString();
    }
    public static String toString(String[] strings) {
        return toString(strings, DEVICE_ID_SPLITTER);
    }

    // 将字符串转换为字符list，默认分隔符是“;”
    public static List<String> toArray(String str) {
        return toArray(str, DEVICE_ID_SPLITTER);
    }

    public static List<String> toArray(String str, String splitter) {
        return Arrays.asList(str.split(splitter));
    }

    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            Float.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String[] remove(String[] strings, String toBeRemoved) {
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        list.remove(toBeRemoved);
        return list.toArray(new String[]{});
    }

    public static String[] addToFirst(String[] strings, String toBeAdded) {
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        list.add(0, toBeAdded);
        return list.toArray(new String[]{});
    }
}
