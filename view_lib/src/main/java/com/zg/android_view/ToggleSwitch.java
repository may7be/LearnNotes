package com.zg.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qwang on 2016/9/2.
 */

public class ToggleSwitch extends LinearLayout {
    private static final String TAG = ToggleSwitch.class.getSimpleName();

    public interface OnSwitchListener {
        void onSwitch(ToggleSwitch slidSwitch, int selectedIndex, String text);
    }

    private static final int DEFAULT_CORNERS = 0; // DP

    private List<String> mSwitchTexts = new ArrayList<String>() {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean add(String o) {
            if (o == null) {
                return false;
            }
            return super.add(o);
        }
    };
    private OnSwitchListener mOnSwitchListener;
    private int mSwitchButtonTextColor = Color.WHITE;
    private int mBackgroundTextColor = Color.DKGRAY;
    private int mBackgroundColor = Color.LTGRAY;
    private int mSwitchButtonColor = Color.rgb(0x00, 0x99, 0xCB);
    private int mBackgroundCorners = 0;
    private int mBackgroundTextSize = 0;
    private int mDividerColor = -1;
    private int mDividerPadding = 0;
    private int mDividerWidth = 0;
    private int mSelectedIndex = 0;

    public ToggleSwitch(Context context) {
        super(context);
        init(context, null);
    }

    public ToggleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(RadioGroup.HORIZONTAL);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ToggleSwitch);
            CharSequence[] textsArray = typedArray.getTextArray(R.styleable.ToggleSwitch_textArray);

            if (textsArray != null) {
                for (CharSequence c : textsArray) {
                    mSwitchTexts.add(c.toString());
                }
            } else {
                mSwitchTexts.add(typedArray.getString(R.styleable.ToggleSwitch_textLeft));
                mSwitchTexts.add(typedArray.getString(R.styleable.ToggleSwitch_textCenter));
                mSwitchTexts.add(typedArray.getString(R.styleable.ToggleSwitch_textRight));
            }
            mBackgroundColor = typedArray.getColor(R.styleable.ToggleSwitch_backgroundColor, mBackgroundColor);
            mBackgroundTextColor =
                    typedArray.getColor(R.styleable.ToggleSwitch_backgroundTextColor, mBackgroundTextColor);
            mBackgroundCorners = (int) typedArray.getDimension(R.styleable.ToggleSwitch_backgroundCorners,
                    dp2px(context, DEFAULT_CORNERS));
            mSwitchButtonTextColor =
                    typedArray.getColor(R.styleable.ToggleSwitch_switchButtonTextColor, mSwitchButtonTextColor);
            mSwitchButtonColor = typedArray.getColor(R.styleable.ToggleSwitch_switchButtonColor, mSwitchButtonColor);
            mBackgroundTextSize = (int) typedArray.getDimension(R.styleable.ToggleSwitch_backgroundTextSize, 0);
            mDividerColor = typedArray.getColor(R.styleable.ToggleSwitch_ss_dividerColor, mDividerColor);
            mDividerPadding = (int) typedArray.getDimension(R.styleable.ToggleSwitch_ss_dividerPadding, 0);
            mDividerWidth = (int) typedArray.getDimension(R.styleable.ToggleSwitch_dividerWidth, 0);
            String selectIndex = typedArray.getString(R.styleable.ToggleSwitch_selectIndex);
            try {
                mSelectedIndex = Integer.valueOf(selectIndex);
            } catch (Exception e) {
                mSelectedIndex = 0;
            }
            // mSelectedIndex =
            // typedArray.getInteger(R.styleable.ToggleSwitch_selectIndex,
            // mSelectedIndex);
            typedArray.recycle();
        }
        defaultBackground = getBackground();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new Runnable() {
            @Override
            public void run() {
                initBackground();
                initTextContainer();
            }
        });
    }

    private GradientDrawable switchButtonLeftCorners;
    private GradientDrawable switchButtonRightCorners;

    private void initBackground() {
        Drawable background = getBackground();
        if (background == null) {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(mBackgroundCorners);
            drawable.setColor(mBackgroundColor);
            setBackground(drawable);
        }
        createBackgroundByEnableState();
    }

    private void initTextContainer() {
        removeAllViews();
        if (mDividerColor != -1) {
            setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(mDividerColor);
            drawable.setSize(mDividerWidth, 1);
            setDividerPadding(mDividerPadding);
            setDividerDrawable(drawable);
        }
        setOrientation(LinearLayout.HORIZONTAL);

        OnClickListener onClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isEnabled()) {
                    int index = (int) v.getTag();
                    setSelectedIndex(index);
                }
            }
        };
        for (int i = 0; i < mSwitchTexts.size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setTag(i);
            tv.setGravity(Gravity.CENTER);
            tv.setText(mSwitchTexts.get(i));
            setBackgroundTextColorByEnableState(tv);
            if (mBackgroundTextSize > 0) {
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mBackgroundTextSize);
            }
            addView(tv, (getWidth() - (mDividerWidth * (mSwitchTexts.size() - 1))) / mSwitchTexts.size(),
                    ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setOnClickListener(onClickListener);
        }
        if (mSelectedIndex >= 0 && mSelectedIndex < mSwitchTexts.size()) {
            setButtonsState(mSelectedIndex, true);
        }
    }

    private Drawable defaultBackground;

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            initBackground();
            initTextContainer();
            setBackground(defaultBackground);
        } else {
//            GradientDrawable drawable = new GradientDrawable();
//            drawable.setCornerRadius(mBackgroundCorners);
//            drawable.setColor(Color.LTGRAY);
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.stroke1dp_gray_corners5dp));

            createBackgroundByEnableState();

            removeAllViews();

            setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.GRAY);
            drawable.setSize(mDividerWidth, 1);
            setDividerPadding(mDividerPadding);
            setDividerDrawable(drawable);

            setOrientation(LinearLayout.HORIZONTAL);

            OnClickListener onClickListener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (isEnabled()) {
                        int index = (int) v.getTag();
                        setSelectedIndex(index);
                    }
                }
            };
            for (int i = 0; i < mSwitchTexts.size(); i++) {
                TextView tv = new TextView(getContext());
                tv.setTag(i);
                tv.setGravity(Gravity.CENTER);
                tv.setText(mSwitchTexts.get(i));
                setBackgroundTextColorByEnableState(tv);
                if (mBackgroundTextSize > 0) {
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mBackgroundTextSize);
                }
                addView(tv, (getWidth() - (mDividerWidth * (mSwitchTexts.size() - 1))) / mSwitchTexts.size(),
                        ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setOnClickListener(onClickListener);
            }
            if (mSelectedIndex >= 0 && mSelectedIndex < mSwitchTexts.size()) {
                setButtonsState(mSelectedIndex, true);
            }
        }
    }

    public void createBackgroundByEnableState() {
        switchButtonLeftCorners = new GradientDrawable();
        switchButtonLeftCorners.setCornerRadii(new float[]{mBackgroundCorners, mBackgroundCorners, 0, 0, 0, 0,
                mBackgroundCorners, mBackgroundCorners});
        switchButtonRightCorners = new GradientDrawable();
        switchButtonRightCorners.setCornerRadii(new float[]{0, 0, mBackgroundCorners, mBackgroundCorners,
                mBackgroundCorners, mBackgroundCorners, 0, 0});
        if (isEnabled()) {
            switchButtonLeftCorners.setColor(mSwitchButtonColor);
            switchButtonRightCorners.setColor(mSwitchButtonColor);
        } else {
            switchButtonLeftCorners.setColor(Color.GRAY);
            switchButtonRightCorners.setColor(Color.GRAY);
        }
    }

    public void setBackgroundTextColorByEnableState(TextView textView) {
        if (isEnabled()) {
            textView.setTextColor(mBackgroundTextColor);
        } else {
            textView.setTextColor(Color.GRAY);
        }
    }

    public void setEnabled(boolean enabled, int seconds) {
        setEnabled(enabled);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        setEnabled(true);
                    }
                });
                cancel();
            }
        }, seconds * 1000);
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setOnSwitchListener(OnSwitchListener l) {
        mOnSwitchListener = l;
    }

    public void setLabels(String... labels) {
        setLabels(Arrays.asList(labels));
    }

    public void setLabels(List<String> labels) {
        mSwitchTexts.clear();
        mSwitchTexts.addAll(labels);
        post(new Runnable() {
            @Override
            public void run() {
                initTextContainer();
            }
        });
    }

    public void setSelectedIndex(int i) {
        setSelectedIndex(i, true);
    }

    public void setSelectedIndex(final int index, final boolean callback) {
        if (index == mSelectedIndex || !isEnabled()) {
            return;
        }
        mSelectedIndex = index;
        post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < getChildCount(); i++) {
                    setButtonsState(i, i == index);
                }
                if (callback && mOnSwitchListener != null) {
                    mOnSwitchListener.onSwitch(ToggleSwitch.this, mSelectedIndex, mSwitchTexts.get(index));
                }
            }
        });
    }

    public void clearSelection() {
        final int position = getSelectedIndex();
        post(new Runnable() {
            @Override
            public void run() {
                setButtonsState(position, false);
                mSelectedIndex = -1;
            }
        });
    }

    private void setButtonsState(int childIndex, boolean isSelected) {
        if (childIndex < 0) {
            return;
        }
        TextView btn = (TextView) getChildAt(childIndex);
        if (isSelected) {
            if (childIndex == 0) {
                btn.setBackground(switchButtonLeftCorners);
            } else if (childIndex == getChildCount() - 1) {
                btn.setBackground(switchButtonRightCorners);
            } else {
                btn.setBackgroundColor(mSwitchButtonColor);
            }
            btn.setTextColor(mSwitchButtonTextColor);
        } else {
            btn.setBackgroundColor(Color.TRANSPARENT);
            setBackgroundTextColorByEnableState(btn);
        }
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }
}
