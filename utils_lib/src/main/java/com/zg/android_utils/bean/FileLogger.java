package com.zg.android_utils.bean;

import android.util.Log;

import com.zg.android_utils.BuildConfig;
import com.zg.android_utils.util_common.LogUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import okio.Okio;

public class FileLogger {
    private static final short MAX_LOG_FILE_SIZE = 10;
    private static final SimpleDateFormat logTimestampFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    private File logDir;

    public FileLogger(File logDir) {
        if (logDir == null) {
            return;
        }
        if (logDir.exists() && logDir.list() != null) {
            while (logDir.list().length > MAX_LOG_FILE_SIZE) {
                String[] files = logDir.list();
                Arrays.sort(files);
                new File(logDir.getAbsolutePath() + File.separator + files[0]).getAbsoluteFile().delete();
            }
        }
        this.logDir = logDir;
    }

    public void log(String message) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        try {
            Okio.buffer(Okio.appendingSink(getLogFile())).writeUtf8(logTimestampFormatter.format(System.currentTimeMillis()) + " " + message + "\n").close();
        } catch (Exception e) {
            Log.e(FileLogger.class.getName(), e.getMessage());
        }
    }

    private File getLogFile() throws IOException {
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        File file = new File(logDir.getAbsolutePath() + File.separator + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".log");
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
