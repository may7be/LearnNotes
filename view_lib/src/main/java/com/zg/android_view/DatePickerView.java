package com.zg.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zg.android_view.databinding.DialogFilterPressLogFiltrateDateBinding;
import com.zg.android_view.util.BaseUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 点击左右箭头减、加日期，点击日期出现弹窗选择日期
 * Created by LiuYiLing on 2017/5/23.
 */

public class DatePickerView extends LinearLayout {
    private TextView tvDate;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Date chooseDate = null; //选中的日期，根据选中的日期变化
    private Date currentDate = null;//今天日期
    private boolean isChooseDay = true;
    private int mDateTextColor = Color.parseColor("#007aff");     // 日期字体颜色
    private float mDateTextSize = 32f;    //日期字体大小
    private int mMaxDaySection;     //最大查询日期，为0时：查询全部日期
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private int currentYear = calendar.get(Calendar.YEAR);
    private int currentMonth = calendar.get(Calendar.MONTH);
    private int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    private DateChangeListener dateListener;
    private DialogFilterPressLogFiltrateDateBinding dateBinding;

    public DatePickerView(Context context) {
        super(context, null);
        init(context);
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DatePickerView);
        mDateTextColor = typedArray.getColor(R.styleable.DatePickerView_tv_date_color, mDateTextColor);
        mDateTextSize = typedArray.getDimension(R.styleable.DatePickerView_tv_date_size, mDateTextSize);
        mMaxDaySection = typedArray.getInt(R.styleable.DatePickerView_day_section, mMaxDaySection);
        init(context);
        typedArray.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.date_choose_item, this);
        tvDate = findViewById(R.id.tv_date);
        rightArrow = findViewById(R.id.iv_right);
        leftArrow = findViewById(R.id.iv_left);
        tvDate.setTextColor(mDateTextColor);
        tvDate.setTextSize(mDateTextSize / 2);
        getLongTime(new Date(System.currentTimeMillis()));
        setButtonListener();
    }

    private void setButtonListener() {
        leftArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                minusOrAddDate(addDay(chooseDate, -1));
            }
        });
        rightArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                minusOrAddDate(addDay(chooseDate, 1));
            }
        });
        tvDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new FilterDateDialog(getContext()).show();
            }
        });
    }

    private Date addDay(Date currentDay, int dayIncrement) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDay);
        c.add(Calendar.DAY_OF_MONTH, dayIncrement);
        return c.getTime();
    }

    //左右箭头减、加日期
    private void minusOrAddDate(Date date) {
        if (mMaxDaySection > 0 && (currentDate.getTime() - date.getTime()) > mMaxDaySection * 24 * 60 * 60 * 1000) {
            Toast.makeText(BaseUtil.getContext(), "请选择今天日期前" + mMaxDaySection + "天内的日期", Toast.LENGTH_SHORT);
        }
        chooseDate = date;
        tvDate.setText(formatter.format(chooseDate));

        onDayChangeListener();
    }

    //日期选择弹窗
    private class FilterDateDialog extends BaseDialog {
        public FilterDateDialog(Context context) {
            super(context);
            setTitle("选择日期");
            setContentView(R.layout.dialog_filter_press_log_filtrate_date);
            dateBinding = (DialogFilterPressLogFiltrateDateBinding) getBinding();
            addCancelButton();
            addButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isChooseDay) {
                        onDayChangeListener();
                        tvDate.setText(formatter.format(chooseDate));
                        dismiss();
                    }
                }
            });
            initDateData();
        }
    }

    //选择日期并判断是否超过7天
    private void initDateData() {
        dateBinding.datePicker.init(currentYear, currentMonth, currentDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                try {
                    currentDate = formatter.parse(currentYear + "-" + (currentMonth + 1) + "-" + currentDay);
                    chooseDate = formatter.parse(year + "-" + month + "-" + dayOfMonth);
                    if (mMaxDaySection == 0) {
                    } else {
                        if (chooseDate.getTime() > currentDate.getTime() || (currentDate.getTime() - chooseDate.getTime()) > mMaxDaySection * 24 * 60 * 60 * 1000) {
                            Toast.makeText(BaseUtil.getContext(), "请选择今天日期前" + mMaxDaySection + "天内的日期", Toast.LENGTH_SHORT).show();
                            isChooseDay = false;
                        } else {
                            isChooseDay = true;
                        }
                    }
                } catch (ParseException e) {
                    Log.e("日期选择", e.getMessage());
                }
            }
        });
    }

    private Long getLongTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String day = formatter.format(date);
        Date dateDetail = null;
        try {
            dateDetail = formatter.parse(day);
            currentDate = dateDetail;
            chooseDate = currentDate;
            tvDate.setText(day);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return dateDetail.getTime();
        }
    }

    public void onDayChangeListener() {
        if (dateListener != null) {
            dateListener.dateChanged(chooseDate.getTime());
        }
    }

    public DateChangeListener getDateListener() {
        return dateListener;
    }

    public void setDateListener(DateChangeListener dateListener) {
        this.dateListener = dateListener;
    }

    public interface DateChangeListener {
        void dateChanged(Long dateTime);
    }
}
