package demo.com.debugutil;

import android.os.Environment;

import java.io.File;

import demo.com.debugutil.util.SDCardUtils;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/4
 * TODO:
 */
public class BaseConfig {
    public static final File LOG_DIR = new File(Environment.getExternalStorageDirectory() + "/" + SDCardUtils.PROJECT_NAME + "/AppLog");
    public static final File PERFORMANCE_LOG_DIR = new File(Environment.getExternalStorageDirectory() + "/" + SDCardUtils.PROJECT_NAME + "/PerformanceLog");
    public static final File START_AND_STOP_LOG_DIR = new File(Environment.getExternalStorageDirectory() + "/" + SDCardUtils.PROJECT_NAME + "/StartAndStop");
}
