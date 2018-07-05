package com.zg.android_view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @description ${DESC}
 * Created by Liu Huanbin
 * 2017/3/14
 */

public class SideMenuSectionAdapter extends BaseSectionQuickAdapter<SideMenuItem, BaseViewHolder> {
    private int mHeaderBackgroundColor;
    private int mHeaderTextColor;
    private float mHeaderTextSize;
    private int mItemTextColor;
    private float mItemTextSize;
    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SideMenuSectionAdapter(int layoutResId, int sectionHeadResId, List<SideMenuItem> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SideMenuItem item) {
        if (mHeaderTextSize > 0) {
            setTextSize(helper, R.id.tv_menu_content, mHeaderTextSize);
        }
        if (mHeaderBackgroundColor != 0) {
            helper.setBackgroundColor(R.id.layout_menu, mHeaderBackgroundColor);
        }
        if (mHeaderTextColor != 0) {
            helper.setTextColor(R.id.tv_menu_content, mHeaderTextColor);
        }
        helper.setText(R.id.tv_menu_content, item.header);
        if (item.getIconUrl() == null) {
            helper.setVisible(R.id.iv_menu_icon, false);
        } else {
            // TODO: 2017/3/11 add icon
        }
        helper.setVisible(R.id.iv_child_menu, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, SideMenuItem item) {
        if (mItemTextSize > 0) {
            setTextSize(helper, R.id.tv_menu_content, mItemTextSize);
        }
        if (mItemTextColor != 0) {
            helper.setTextColor(R.id.tv_menu_content, mItemTextColor);
        }
        helper.setText(R.id.tv_menu_content, item.getNameStr());
        if (item.getIconUrl() == null) {
            helper.setVisible(R.id.iv_menu_icon, false);
        } else {
            // TODO: 2017/3/11 add icon
            ImageView circleImageView = helper.getView (R.id.iv_menu_icon);
            Glide.with(context).load(item.getIconUrl()).fitCenter()
             /*
	          * 缺省的占位图片，一般可以设置成一个加载中的进度GIF图
	          */
                    .crossFade().error(R.drawable.person_center_header).into(circleImageView);
        }
        if (item.isChild() || item.getChildcnt() > 0) {
            helper.setVisible(R.id.iv_child_menu, true);
        } else {
            helper.setVisible(R.id.iv_child_menu, false);
        }
    }
    public void setContext(Context context1){this.context=context1;}

    private void setTextSize(BaseViewHolder holder, int viewId, float size) {
        TextView view = holder.getView(viewId);
        view.setTextSize(size);
    }

    public void setmHeaderBackgroundColor(int mHeaderBackgroundColor) {
        this.mHeaderBackgroundColor = mHeaderBackgroundColor;
    }

    public void setmHeaderTextColor(int mHeaderTextColor) {
        this.mHeaderTextColor = mHeaderTextColor;
    }

    public void setmHeaderTextSize(float mHeaderTextSize) {
        this.mHeaderTextSize = mHeaderTextSize;
    }

    public void setmItemTextColor(int mItemTextColor) {
        this.mItemTextColor = mItemTextColor;
    }

    public void setmItemTextSize(float mItemTextSize) {
        this.mItemTextSize = mItemTextSize;
    }
}
