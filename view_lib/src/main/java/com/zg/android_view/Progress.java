package com.zg.android_view;

import android.app.ProgressDialog;
import android.content.Context;

public class Progress extends ProgressDialog {

    private Context context;

    private Progress(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    private static Progress dialog;

    public static void showLoad(Context context) {
        show(context, "正在加载...");
    }

    public static void show(Context context, String str) {
        try {
            if (dialog == null) {
                dialog = new Progress(context);
                dialog.setCancelable(false);
            } else {
                if (dialog.context != context) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    dialog = new Progress(context);
                    dialog.setCancelable(false);
                }
            }
            if (!dialog.isShowing()) {
                dialog = new Progress(context);
                dialog.setCancelable(false);
                dialog.setMessage(str);
                dialog.show();
            }
        } catch (Exception e) {
            // TODO: handle exception
            dialog = null;
        }

    }

    public static void hidden() {
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            dialog = null;
        } finally {
            //dialog = null;
        }

    }
}
