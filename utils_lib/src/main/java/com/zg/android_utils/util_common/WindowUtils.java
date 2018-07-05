package com.zg.android_utils.util_common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class WindowUtils {

    private static float finalScale;

    public static int dp2Px(Context ctx, float dip) {
        return (int) (ctx.getResources().getDisplayMetrics().density * dip + 0.5);
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return 转换后的px的值
     */
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取屏幕像素密度
     *
     * @return 像素密度值
     */
    public static float getDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * sp转px
     *
     * @param sp sp值
     * @return 转换后的px值
     */
    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static boolean hideKeyboard(Context ctx, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(windowToken, 0);
    }

    public static void showKeyboard(Context ctx, View view) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        imm.showSoftInput(view, 0);
    }

    public static void setText(Activity activity, int textViewId, String txt) {
        if (activity == null) {
            return;
        }
        View view = activity.findViewById(textViewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(txt);
        }
    }

    public static void setText(View root, int textViewId, String txt) {
        if (root == null) {
            return;
        }
        View view = root.findViewById(textViewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(txt);
        }
    }

    public static String getText(TextView tv) {
        if (tv == null || tv.getText().toString().isEmpty()) {
            return "";
        }
        return tv.getText().toString();
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight() {
        return BaseUtil.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth() {
        return BaseUtil.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 针对dialog进行适当的放缩
     * @param dialog
     */
    public static void scaleWindowToFitDifferentScreen(Dialog dialog) {
        final View rootView = dialog.getWindow().getDecorView().getRootView();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int) (rootView.getMeasuredWidth() * (1 / finalScale));
        layoutParams.height = (int) (rootView.getMeasuredHeight() * (1 / finalScale));
        dialog.getWindow().setAttributes(layoutParams);
    }

    /**
     * 针对activity进行scale,以适配不同的分辨率和dpi
     * @param activity
     */
    public static void scaleWindowToFitDifferentScreen(Activity activity, final int designWidth, final int designHeight) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        final View view = rootView.getChildAt(0);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = dp2px((int) (designHeight / 2));
                int width = dp2px((int) designWidth / 2);
                view.getLayoutParams().height = height;
                view.getLayoutParams().width = width;
                float screenHeight = getScreenHeight();
                float screenWidth = getScreenWidth();
                finalScale = screenWidth / designWidth < screenHeight / designHeight
                        ? screenWidth / designWidth : screenHeight / designHeight;

                view.setScaleY(finalScale * (2 / getDensity()));
                view.setScaleX(finalScale * (2 / getDensity()));
                view.setTranslationY((screenHeight - height) / 2);
                view.setTranslationX((screenWidth - width) / 2);
//                - (AdapterConfig.BASEX - screenWidth) / 2
            }
        });
    }
}
