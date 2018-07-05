package com.cnmts.android_crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.zg.android_utils.util_common.BaseUtil;
import com.zg.android_utils.util_common.FileUtil;
import com.zg.android_utils.util_common.LogUtil;
import com.zg.android_utils.util_common.SDCardUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import okio.Okio;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CRASH";
    // 默认UncaughtExceptionHandler处理类
    private Thread.UncaughtExceptionHandler defaultHandler;
    // Log文件路径
    private String logFilePath;
    // 单例本类对象
    private static CrashHandler crashHandler;

    // 上下文
    private Context context;

    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    boolean b = false;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    // 初始化
    public void init(Context context) {
        this.context = context;
        // 获取默认处理器
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置本类为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private String getLogFilePath() {
        String path = SDCardUtils.getCrashReportPath();
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = "crash-" + SDCardUtils.getFileName() + ".txt";
        return new File(dir, fileName).getAbsolutePath();
    }

    // 处理异常信息
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        logFilePath = getLogFilePath();

        Log.e("exception", "thread name=" + thread.getName(), throwable);
        if (!handleException(throwable) && defaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            defaultHandler.uncaughtException(thread, throwable);
        } else {
            // 杀进程
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            BaseUtil.killProcess();
        }
    }

    private boolean handleException(final Throwable throwable) {

//        App.getContext().stopService(new Intent(App.getContext(), ChatService.class));
        if (throwable == null) {
            b = false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();

                Looper.loop();
            }
        }.start();
        // 收集设备参数信息
        collectDeviceInfo(context);
        // 保存日志文件
        saveCrashInfo2File(throwable);

        b = true;
        return b;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                LogUtil.e(TAG, "an error occured when collect crash info", e);
            }
        }
        //set usrInfo
       /* UserInfo currentUser = AuthManager.getUser();
        if (currentUser != null) {
            infos.put("currentUserId", String.valueOf(currentUser.getObjectId()));
            infos.put("currentUserName", currentUser.getUsername());
            infos.put("currentUserWorkName", currentUser.getWorkerName());
        }*/
        boolean patrol = true;
        infos.put("patrol", String.valueOf(patrol));
    }

    private void saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        try {
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            String result = writer.toString();
            sb.append(result);
            Okio.buffer(Okio.appendingSink(new File(logFilePath))).writeUtf8(sb.toString()).close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(printWriter);
        }
    }
}
