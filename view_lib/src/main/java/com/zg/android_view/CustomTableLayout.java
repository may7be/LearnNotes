package com.zg.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Arrays;

public class CustomTableLayout extends FrameLayout {

    private int rowCount = 1;
    private int columnCount = 1;
    private int verticalCellPadding = 0;
    private int horizontalCellPadding = 0;

    public interface Cell {

        void setPosition(int rowPosition, int columnPosition, int horizontalStretch, int verticalStretch);

        int[] getPosition();
    }

    public CustomTableLayout(Context context) {
        super(context);
        init(null);
    }

    public CustomTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public CustomTableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTableLayout);
            rowCount = a.getInt(R.styleable.CustomTableLayout_rowCount, rowCount);
            columnCount = a.getInt(R.styleable.CustomTableLayout_columnCount, columnCount);
            horizontalCellPadding =
                    a.getDimensionPixelSize(R.styleable.CustomTableLayout_horizontalCellPadding, horizontalCellPadding);
            verticalCellPadding =
                    a.getDimensionPixelSize(R.styleable.CustomTableLayout_verticalCellPadding, verticalCellPadding);
            a.recycle();
        }
    }

    private void layout(View view) {
        float singleCellWidth =
                ((getWidth() - getPaddingLeft() - getPaddingRight()) - (columnCount - 1f) * horizontalCellPadding)
                        / columnCount;
        float singleCellHeight =
                ((getHeight() - getPaddingTop() - getPaddingBottom()) - (rowCount - 1f) * verticalCellPadding)
                        / rowCount;
        if (view.getVisibility() == View.GONE || view.getVisibility() == View.INVISIBLE) {
            return;
        }
        Cell cell = (Cell) view;
        int[] position = cell.getPosition();
        int rowPosition = position[0];
        int columnPosition = position[1];
        int xStretch = position[2];
        xStretch = xStretch <= 0 ? 1 : xStretch;
        xStretch = xStretch > columnCount ? columnCount : xStretch;
        int yStretch = position[3];
        yStretch = yStretch <= 0 ? 1 : yStretch;
        yStretch = yStretch > rowCount ? rowCount : yStretch;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        float floatWidth = singleCellWidth * xStretch + horizontalCellPadding * (xStretch - 1);
        params.width = floatWidth > (int) floatWidth ? (int) floatWidth + 1 : (int) floatWidth;
        float floatHeight = singleCellHeight * yStretch + verticalCellPadding * (yStretch - 1);
        params.height = floatHeight > (int) floatHeight ? (int) floatHeight + 1 : (int) floatHeight;
        view.setLayoutParams(params);
        view.setTranslationY(rowPosition * singleCellHeight + (rowPosition) * verticalCellPadding);
        view.setTranslationX(columnPosition * singleCellWidth + (columnPosition) * horizontalCellPadding);
    }

    private void layoutAll() {
        for (int i = 0; i < getChildCount(); i++) {
            layout(getChildAt(i));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        layoutAll();
    }

    @Override
    public void addView(View child) {
        innerAddView(child);
    }

    @Override
    public void addView(View child, int index) {
        innerAddView(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        innerAddView(child);
    }

    public void addView(View view, int[] cellPosition) {
        if (cellPosition == null) {
            addView(view);
        } else {
            addView(view, cellPosition[0], cellPosition[1], cellPosition[2], cellPosition[3]);
        }
    }

    public void addView(View view, int rowPosition, int columnPosition, int horizontalStretch, int verticalStretch) {
        TableCell cell = new TableCell(getContext());
        cell.setPosition(rowPosition, columnPosition, horizontalStretch, verticalStretch);
        cell.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        innerAddView(cell);
    }

    private void innerAddView(final View v) {
        if (!(v instanceof Cell)) {
            throw new IllegalArgumentException("child view must implements Cell");
        }
        Cell cell = (Cell) v;
        if (cell.getPosition() == null) {
            if (getChildCount() <= 0) {
                cell.setPosition(0, 0, 1, 1);
            } else {
                int[] lastCellPosition = ((Cell) getChildAt(getChildCount() - 1)).getPosition();
                Log.i("kim", "last: " + Arrays.toString(lastCellPosition));
                int rowPosition = lastCellPosition[1] + lastCellPosition[2] >= columnCount ? lastCellPosition[0] + 1
                        : lastCellPosition[0];
                int columnPosition = rowPosition > lastCellPosition[0] ? 0 : lastCellPosition[1] + lastCellPosition[2];
                Log.i("kim", rowPosition + ", " + columnPosition);
                cell.setPosition(rowPosition, columnPosition, 1, 1);
            }
        }
        super.addView(v, getChildCount());
        layoutAll();
        post(new Runnable() {

            @Override
            public void run() {
                layoutAll();
            }
        });
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getVerticalCellPadding() {
        return verticalCellPadding;
    }

    public void setVerticalCellPadding(int verticalCellPadding) {
        this.verticalCellPadding = verticalCellPadding;
    }

    public int getHorizontalCellPadding() {
        return horizontalCellPadding;
    }

    public void setHorizontalCellPadding(int horizontalCellPadding) {
        this.horizontalCellPadding = horizontalCellPadding;
    }
}
