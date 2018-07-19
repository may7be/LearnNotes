package com.demo;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.bean.DispatchBean;
import com.demo.myapplication.R;

import java.util.List;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/10
 * TODO:
 */
public class DispatchAdapter extends BaseQuickAdapter<DispatchBean, BaseViewHolder> {

    public DispatchAdapter(int layoutResId, @Nullable List<DispatchBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, DispatchBean item) {
        helper.getView(R.id.tv1).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick: tv--" + helper.getAdapterPosition());
                helper.getView(R.id.tv1).setVisibility(View.GONE);
                helper.getView(R.id.et1).setVisibility(View.VISIBLE);
                return true;
            }
        });
        helper.getView(R.id.et1).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "onFocusChange: et--" + helper.getAdapterPosition() + hasFocus);
                if (hasFocus) {

                } else {
                    helper.getView(R.id.tv1).setVisibility(View.VISIBLE);
                    helper.getView(R.id.et1).setVisibility(View.GONE);
                }
            }
        });
    }
}
