package com.zg.android_view.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by ZhaoKeqiang on 2017/8/30.
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
}
