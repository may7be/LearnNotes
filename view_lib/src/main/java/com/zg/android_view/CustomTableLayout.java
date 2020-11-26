package com.zg.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;

public class CustomTableLayout extends ViewGroup {

    private int mRowCount = 1;//行数
    private int mColumnCount = 1;//列数
    private float mVerticalCellPadding = 0f;//竖直方向上子View之间的间距
    private float mHorizontalCellPadding = 0f;//水平方向上子View之间的间距
    private float mSingleCellWidth = 0f;
    private float mSingleCellHeight = 0f;

    public CustomTableLayout(Context context) {
        this(context, null);
    }

    public CustomTableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomTableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupProperties(context, attrs);
    }

    private void setupProperties(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTableLayout);

        this.mRowCount = ta.getInteger(R.styleable.CustomTableLayout_rowSize, this.mRowCount);
        this.mColumnCount = ta.getInteger(R.styleable.CustomTableLayout_columnSize, this.mColumnCount);
        this.mVerticalCellPadding = ta.getDimension(R.styleable.CustomTableLayout_verticalCellSpacing,
                this.mVerticalCellPadding);
        this.mHorizontalCellPadding = ta.getDimension(R.styleable.CustomTableLayout_horizontalCellSpacing,
                this.mHorizontalCellPadding);

        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int finalWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : 0;
        int finalHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : 0;

        long startTime = System.currentTimeMillis();
        measureAllChild(finalWidth, finalHeight);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        Log.i(GridLayout.class.getSimpleName(), "measureChildren cost time = " + (System.currentTimeMillis() - startTime));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void measureAllChild(int parentWidth, int parentHeight) {
        //根据容器的宽高算出一行一列的大小
        this.mSingleCellWidth =
                ((parentWidth - getPaddingLeft() - getPaddingRight()) - (this.mColumnCount - 1) * this.mHorizontalCellPadding)
                        / this.mColumnCount;
        this.mSingleCellHeight =
                ((parentHeight - getPaddingTop() - getPaddingBottom()) - (this.mRowCount - 1) * this.mVerticalCellPadding)
                        / this.mRowCount;

        for (int i = 0; i < getChildCount(); i++) {
            measureSingleChild(getChildAt(i), this.mSingleCellWidth, this.mSingleCellHeight);
        }
    }

    private void measureSingleChild(View childView, float singleCellWidth, float singleCellHeight) {
        final int rowCount = this.mRowCount;
        final int columnCount = this.mColumnCount;
        final float horizontalCellPadding = this.mHorizontalCellPadding;
        final float verticalCellPadding = this.mVerticalCellPadding;

        if (childView.getVisibility() == View.GONE || childView.getVisibility() == View.INVISIBLE) {
            return;
        }
        checkIsCell(childView);

        Cell cell = (Cell) childView;
        int[] position = cell.getPosition();
        int xStretch = position[2];//占用的列数
        xStretch = xStretch <= 0 ? 1 : xStretch;
        xStretch = xStretch > columnCount ? columnCount : xStretch;

        int yStretch = position[3];//占用的行数
        yStretch = yStretch <= 0 ? 1 : yStretch;
        yStretch = yStretch > rowCount ? rowCount : yStretch;

        float childWidth = singleCellWidth * xStretch + horizontalCellPadding * (xStretch - 1);
        float childHeight = singleCellHeight * yStretch + verticalCellPadding * (yStretch - 1);
        ViewGroup.LayoutParams lp = childView.getLayoutParams();
        lp.width = (int) childWidth;
        lp.height = (int) childHeight;
        Log.i(GridLayout.class.getSimpleName(), " measure singleView width = " + childWidth +
                " singleView height = " + childHeight);
        childView.setLayoutParams(lp);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChildren();
    }

    private void layoutChildren() {
        final float singleCellWidth = this.mSingleCellWidth;
        final float singleCellHeight = this.mSingleCellHeight;
        final float horizontalCellPadding = this.mHorizontalCellPadding;
        final float verticalCellPadding = this.mVerticalCellPadding;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            checkIsCell(childView);

            Cell cell = (Cell) childView;
            final int[] position = cell.getPosition();
            int rowPosition = position[0];
            int columnPosition = position[1];

            float left = columnPosition * singleCellWidth + columnPosition * horizontalCellPadding;
            float top = rowPosition * singleCellHeight + rowPosition * verticalCellPadding;
            Log.i(GridLayout.class.getSimpleName(), " layout singleCellView width = " + childView.getMeasuredWidth() +
                    " singleCellView height = " + childView.getMeasuredHeight());
            childView.layout((int) left, (int) top, (int) (left + childView.getMeasuredWidth()), (int) (top + getMeasuredHeight()));
        }
    }

    private void checkIsCell(View view) {
        if (!(view instanceof Cell)) {
            throw new ClassCastException("childView is can not cast to Cell");
        }
    }

    public void setRowAndColumnCount(int rowCount, int columnCount) {
        if (rowCount == mRowCount && columnCount == mColumnCount) {
            return;
        }
        mRowCount = rowCount;
        mColumnCount = columnCount;
        requestLayout();
    }

    public void addView(@NonNull View view, @NonNull int[] cellPosition) {
        addView(view, cellPosition[0], cellPosition[1], cellPosition[2], cellPosition[3]);
    }

    public void addView(@NonNull View view, int rowPosition, int columnPosition, int horizontalStretch, int verticalStretch) {
        GridCell gridCell = new GridCell(getContext());
        gridCell.setPosition(rowPosition, columnPosition, horizontalStretch, verticalStretch);
        gridCell.addView(view);
        addView(gridCell, getChildCount());
    }

    public int getRowCount() {
        return mRowCount;
    }

    public int getColumnCount() {
        return mColumnCount;
    }

    public static class GridCell extends FrameLayout implements Cell {
        private int[] position;

        public GridCell(@NonNull Context context) {
            super(context);
        }

        public GridCell(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public GridCell(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public void setPosition(int rowPosition, int columnPosition, int horizontalStretch, int verticalStretch) {
            this.position = new int[]{
                    rowPosition, columnPosition, horizontalStretch, verticalStretch};
        }

        @Override
        public int[] getPosition() {
            return position;
        }
    }

    public interface Cell {

        /**
         * Sets the location of the child view in the parent container
         *
         * @param rowPosition       Child view position int the row
         * @param columnPosition    Child view position int the column
         * @param horizontalStretch The number of columns occupied in the horizontal direction of the subview
         * @param verticalStretch   The number of rows occupied in the vertical direction of the subview
         */
        void setPosition(int rowPosition, int columnPosition, int horizontalStretch, int verticalStretch);

        /**
         * Gets the location of the child view in the parent container
         *
         * @return int[] size must is four
         * int[0]:rowPosition,int[1]:columnPosition,int[2]:horizontalStretch,int[3]:verticalStretch
         */
        int[] getPosition();
    }
}
