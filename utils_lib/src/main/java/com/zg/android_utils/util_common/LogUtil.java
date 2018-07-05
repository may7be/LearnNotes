package com.zg.android_utils.util_common;

import android.os.Environment;
import android.util.Log;

import com.zg.android_utils.BuildConfig;
import com.zg.android_utils.bean.FileLogger;

import java.io.File;

public class LogUtil {

    private enum LogType {
        d, i, e
    }

    private static String PACKAGE_NAME = "smart_message";
    private static Boolean isDebug = BuildConfig.DEBUG;
    public static final File LOG_DIR = new File(Environment.getExternalStorageDirectory() + "/" + SDCardUtils.PROJECT_NAME + "/AppLog");
    public static final File PERFORMANCE_LOG_DIR = new File(Environment.getExternalStorageDirectory() + "/" + SDCardUtils.PROJECT_NAME + "/PerformanceLog");

    private static FileLogger fileLogger = new FileLogger(LOG_DIR);
    private static FileLogger performanceFileLogger = new FileLogger(PERFORMANCE_LOG_DIR);

    public static void e(String tag, String msg, Throwable e) {
        log(tag, msg, LogType.e, e, true);
    }

    public static void e(String tag, String msg, Throwable e, boolean logToFile) {
        log(tag, msg, LogType.e, e, logToFile);
    }

    public static void e(String tag, Throwable e) {
        log(tag, null, LogType.e, e, true);
    }

    public static void e(String tag, Throwable e, boolean logToFile) {
        log(tag, null, LogType.e, e, logToFile);
    }

    public static void e(String tag, String msg) {
        log(tag, msg, LogType.e, null, true);
    }

    public static void e(String tag, String msg, boolean logToFile) {
        log(tag, msg, LogType.e, null, logToFile);
    }

    public static void i(String tag, String msg) {
        log(tag, msg, LogType.i, false);
    }

    public static void d(String tag, String msg) {
        log(tag, msg, LogType.d, true);
    }

    public static void d(String tag, String msg, boolean logToFile) {
        log(tag, msg, LogType.d, logToFile);
    }

    public static void p(long startTime, String info) {
        if (isDebug) {
            String msg = String.format("costs %sms (Thread=%s) " + info, System.currentTimeMillis() - startTime, Thread.currentThread().getName());
            i("performance", msg);
            performanceFileLogger.log(msg);
        }
    }

    private static void log(String tag, String msg, LogType type, boolean logToFile) {
        log(tag, msg, type, null, logToFile);
    }

    public static void log(String tag, String msg, LogType type, Throwable e, boolean logToFile) {
        if (!isDebug) {
            return;
        }
        msg = msg != null ? msg : e.getMessage() != null ? e.getMessage() : "";
        msg = String.format(msg + " (Thread = %s)", Thread.currentThread().getName());
        switch (type) {
            case i:
                Log.i(tag, msg);
                break;
            case d:
                Log.i(tag, msg);
                break;
            case e:
                if (e == null) {
                    Log.e(tag, msg);
                } else {
                    Log.e(tag, msg, e);
                }
                break;

        }
        if (logToFile) {
            fileLogger.log(tag + ": " + msg);
        }
    }

    private static String getStackTrace() {
        StringBuffer result = new StringBuffer();
        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
            String className = e.getClassName();
            if (className.contains(PACKAGE_NAME) && !className.contains(LogUtil.class.getName())) {
                result.append(String.format("%s.%s() line %s <- ", className, e.getMethodName(), e.getLineNumber()));
            }
        }
        return result.substring(0, result.length() - 4);
    }
}
