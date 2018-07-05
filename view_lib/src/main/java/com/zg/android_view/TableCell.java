package com.zg.android_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class TableCell extends FrameLayout implements CustomTableLayout.Cell {

    private int[] position;

    public TableCell(Context context) {
        super(context);
    }

    public TableCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TableCell(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setPosition(int rowPosition, int columnPosition, int horizontalStretch, int verticalStretch) {
        position = new int[]{rowPosition, columnPosition, horizontalStretch, verticalStretch};
    }

    @Override
    public int[] getPosition() {
        return position;
    }

}
