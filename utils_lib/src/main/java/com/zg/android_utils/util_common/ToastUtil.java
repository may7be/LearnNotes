package com.zg.android_utils.util_common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(final Context context, final String info) {
        if (info == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(BaseUtil.getContext(), info, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(info);
                }
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        });
    }

    public static void showToast(Context context, int stringId) {
        showToast(context, context.getResources().getString(stringId));
    }

    public static void showToast(String msg) {
        showToast(BaseUtil.getContext(), msg);
    }

    public static void showToast(final Throwable e) {
        if (e == null) {
            return;
        }
        showToast(BaseUtil.getContext(), e.getMessage());
    }

    public static void showToast(int msgId) {
        showToast(BaseUtil.getContext(), BaseUtil.getContext().getString(msgId));
    }

}