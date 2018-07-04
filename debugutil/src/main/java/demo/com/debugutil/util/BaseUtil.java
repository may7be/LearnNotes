package demo.com.debugutil.util;

import android.app.Application;
import android.content.Context;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/4
 * TODO:
 */
public class BaseUtil {
    private static Context context;

    static {
        try {
            context = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null, (Object[]) null);
            if (context == null) {
                context = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null, (Object[]) null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void killProcess() {
//        ActivityManager.closeAllActivity;
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
