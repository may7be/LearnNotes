package com.zg.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author NiYang
 * @Description:
 * @date 2017/8/29 18:06
 */

public class OutsideTextToggleButton extends LinearLayout {
    private static final String TAG = OutsideTextToggleButton.class.getSimpleName();
    private String mLeftText = null;
    private String mRightText = null;
    private float mTextSize = 0f;
    private float mToggleButtonMargin = 0f;
    private int mDefaultTextColor = 0;
    private int mCheckedLeftTextColor = 0;
    private int mCheckedRightTextColor = 0;
    private Drawable mToggleButtonBackground = null;
    private ToggleButton mToggleButton = null;
    private TextView mLeftTextView = null;
    private TextView mRightTextView = null;
    private boolean isEnabled = false;
    private OnCheckedChangeListener listener = null;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (listener != null) {
                listener.onCheckedChanged(compoundButton, b);
            }
        }
    };

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CompoundButton compoundButton, boolean b);
    }

    public OutsideTextToggleButton(Context context) {
        this(context, null);
    }

    public OutsideTextToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        if (attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.OutsideTextToggleButton);
            this.mLeftText = typedArray.getString(R.styleable.OutsideTextToggleButton_leftText);
            this.mRightText = typedArray.getString(R.styleable.OutsideTextToggleButton_rightText);
            this.mTextSize = typedArray.getDimension(R.styleable.OutsideTextToggleButton_toggleButtonTextSize, 30f);
            this.mDefaultTextColor = typedArray.getColor(R.styleable.OutsideTextToggleButton_defaultTextColor, ContextCompat.getColor(getContext(), R.color.text_color_gray_dark));
            this.mCheckedLeftTextColor = typedArray.getColor(R.styleable.OutsideTextToggleButton_checkedLeftTextColor, ContextCompat.getColor(getContext(), R.color.secondary_red));
            this.mCheckedRightTextColor = typedArray.getColor(R.styleable.OutsideTextToggleButton_checkedRightTextColor, ContextCompat.getColor(getContext(), R.color.secondary_green));
            this.mToggleButtonMargin = typedArray.getDimension(R.styleable.OutsideTextToggleButton_toggleButtonMargin, 10f);
            this.mToggleButtonBackground = typedArray.getDrawable(R.styleable.OutsideTextToggleButton_toggleButtonBackground);
            typedArray.recycle();
        }
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec);
        int height = measure(heightMeasureSpec);
        setToggleButtonSize(width, height);
    }

    private int measure(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            if (mode == MeasureSpec.AT_MOST) {
                result = 100;
            } else {
                result = Math.min(size, result);
            }
        }
        return result;
    }

    private void setToggleButtonSize(int width, int height) {
        int toggleButtonWidth = (int) (width - 2 * this.mTextSize - 2 * this.mToggleButtonMargin);
        LayoutParams params = new LayoutParams(toggleButtonWidth, LayoutParams.MATCH_PARENT);
        params.leftMargin = (int) this.mToggleButtonMargin;
        params.rightMargin = (int) this.mToggleButtonMargin;
        this.mToggleButton.setLayoutParams(params);
        if (height < this.mTextSize) {
            this.mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, height);
            this.mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, height);
        }
    }

    private void initView() {
        removeAllViews();
        this.mLeftTextView = new TextView(getContext());
        if (this.mLeftText == null) this.mLeftTextView.setText("关");
        else this.mLeftTextView.setText(this.mLeftText);
        this.mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.mTextSize);
        this.mLeftTextView.setTextColor(this.mDefaultTextColor);
        addView(this.mLeftTextView);
        this.mToggleButton = new ToggleButton(getContext());
        this.mToggleButton.setOnCheckedChangeListener(this.onCheckedChangeListener);
        if (this.mToggleButtonBackground != null) {
            this.mToggleButton.setBackgroundDrawable(this.mToggleButtonBackground);
        } else {
            this.mToggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_toggle_button));
        }
        this.mToggleButton.setTextOff("");
        this.mToggleButton.setTextOn("");
        this.mToggleButton.setText("");
        addView(this.mToggleButton);
        this.mRightTextView = new TextView(getContext());
        if (this.mRightText == null) this.mRightTextView.setText("开");
        else this.mRightTextView.setText(this.mRightText);
        this.mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.mTextSize);
        this.mRightTextView.setTextColor(this.mDefaultTextColor);
        addView(this.mRightTextView);
    }

    public void setLeftTextColor(@ColorInt int color) {
        this.mLeftTextView.setTextColor(color);
    }

    public void setRightTextColor(@ColorInt int color) {
        this.mRightTextView.setTextColor(color);
    }

    public void setTextColor(@ColorInt int color) {
        this.mLeftTextView.setTextColor(color);
        this.mRightTextView.setTextColor(color);
    }

    public void setDefaultTextColor() {
        this.mLeftTextView.setTextColor(this.mDefaultTextColor);
        this.mRightTextView.setTextColor(this.mDefaultTextColor);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    public void setChecked(boolean checked, boolean callback) {
        if (!isEnabled()) {
            return;
        }
        if (!callback) {
            this.mToggleButton.setOnCheckedChangeListener(null);
        }
        this.mToggleButton.setChecked(checked);
        this.mToggleButton.setOnCheckedChangeListener(this.onCheckedChangeListener);
        if (checked) {
            this.mRightTextView.setTextColor(this.mCheckedRightTextColor);
            this.mLeftTextView.setTextColor(this.mDefaultTextColor);
        } else {
            this.mLeftTextView.setTextColor(this.mCheckedLeftTextColor);
            this.mRightTextView.setTextColor(this.mDefaultTextColor);
        }
    }

    public void setEnabled(boolean enabled, int seconds) {
        setEnabled(enabled);
        this.isEnabled = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        isEnabled = false;
                        setEnabled(true);
                    }
                });
                cancel();
            }
        }, seconds * 1000);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (isEnabled) return;
        super.setEnabled(enabled);
        this.mToggleButton.setEnabled(enabled);
    }
}
