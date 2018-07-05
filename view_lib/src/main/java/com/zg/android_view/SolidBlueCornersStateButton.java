package com.zg.android_view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

/**
 * 有蓝色填充色的状态按钮
 * Created by NiYang on 2016/11/18.
 */

public class SolidBlueCornersStateButton extends StateButton {
    private int mNormalStateColor;
    private int mPressedStateColor;
    private int mUnableStateColor;
    private int mTextColor;

    public SolidBlueCornersStateButton(Context context) {
        this(context, null);
    }

    public SolidBlueCornersStateButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SolidBlueCornersStateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getColor();
        setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, Resources.getSystem().getDisplayMetrics()));
        setStateBackgroundColor(this.mNormalStateColor, this.mPressedStateColor, this.mUnableStateColor);
        setStateTextColor(this.mTextColor, this.mTextColor, this.mTextColor);
        setGravity(Gravity.CENTER);
    }

    private void getColor() {
        this.mNormalStateColor = getColor(R.color.primary);
        this.mPressedStateColor = getColor(R.color.primary_dark);
        this.mUnableStateColor = getColor(R.color.gray);
        this.mTextColor = getColor(R.color.white);
    }


    private int getColor(int res) {
        return ContextCompat.getColor(getContext(), res);
    }
}
