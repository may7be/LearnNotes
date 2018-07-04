package demo.com.debugutil.customview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import demo.com.debugutil.R;
import demo.com.debugutil.util.BaseUtil;
import demo.com.debugutil.util.WindowUtils;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/4
 * TODO:
 */
public class CustomPopupWindow extends PopupWindow {
    LinearLayout rootView;

    public CustomPopupWindow() {
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView = new LinearLayout(BaseUtil.getContext());
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setBackgroundResource(R.drawable.shadow_white_bg);
        ScrollView scrollView = new ScrollView(BaseUtil.getContext());
        scrollView.addView(rootView);
        setContentView(scrollView);
        setAnimationStyle(android.R.style.Animation_Dialog);
        setFocusable(true);
        //不加这句 setOutsideTouchable()将不起作用！
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
    }

    public void addMenuItem(int text, View.OnClickListener listener) {
        addMenuItem(BaseUtil.getContext().getResources().getString(text), listener);
    }

    public void addMenuItem(String text, View.OnClickListener listener) {
        TextView tv = new TextView(BaseUtil.getContext());
        tv.setPadding(dp2Px(20), dp2Px(10), dp2Px(20), dp2Px(10));
        tv.setBackgroundResource(R.drawable.transparent_to_gray_selector);
        tv.setTextColor(ContextCompat.getColor(BaseUtil.getContext(), R.color.primary_text));
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        tv.setOnClickListener(listener);
        rootView.addView(tv);
    }

    public void addMenuItem(String text, boolean notify, View.OnClickListener listener) {
        RelativeLayout view = new RelativeLayout(BaseUtil.getContext());
        view.setOnClickListener(listener);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView tv = new TextView(BaseUtil.getContext());
        tv.setPadding(dp2Px(20), dp2Px(10), dp2Px(20), dp2Px(10));
        tv.setBackgroundResource(R.drawable.transparent_to_gray_selector);
        tv.setTextColor(ContextCompat.getColor(BaseUtil.getContext(), R.color.primary_text));
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        view.addView(tv);

        // 红点
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dp2Px(10), dp2Px(10));
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        params.addRule(RelativeLayout.ALIGN_TOP, tv);
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        Button btnNotify = new Button(BaseUtil.getContext());
        btnNotify.setWidth(dp2Px(11));
        btnNotify.setHeight(dp2Px(11));
        btnNotify.setBackgroundResource(R.drawable.bar_red);
        if (notify) {
            btnNotify.setVisibility(View.VISIBLE);
        } else {
            btnNotify.setVisibility(View.GONE);
        }
        view.addView(btnNotify, params);
        rootView.addView(view);
    }

    public View setTitle(String title) {
        TextView tv = new TextView(BaseUtil.getContext());
        tv.setPadding(dp2Px(20), dp2Px(15), dp2Px(20), dp2Px(15));
        tv.setBackgroundResource(R.drawable.transparent_to_gray_selector);
        tv.setTextColor(ContextCompat.getColor(BaseUtil.getContext(), R.color.primary_text));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        tv.setText(title);
        rootView.addView(tv, 0);
        return tv;
    }

    private int dp2Px(int dps) {
        return WindowUtils.dp2Px(BaseUtil.getContext(), dps);
    }
}
