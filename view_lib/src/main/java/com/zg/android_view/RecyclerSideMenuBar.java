package com.zg.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 侧边菜单栏
 * <p>
 * Created by Liu Huanbin
 * 2017/3/14
 */

public class RecyclerSideMenuBar extends RelativeLayout {
    private RecyclerView menuList,subMenuList;
    private TextView tvBack, tvMenuTitle;
    private LinearLayout layoutTitle;
    private SideMenuSectionAdapter sideMenuSectionAdapter;
    private MenuItemClickListener itemClickListener;
    private List<SideMenuItem> sideMenuItemList;
    private Stack<List<SideMenuItem>> cachedList;
    private Stack<String> cachedTitle;
    private SideMenuItem curItem;

    private int mGroupBackground;   // 菜单项分组名背景颜色
    private int mGroupTextColor;    // 菜单项分组名称字体颜色
    private float mGroupTextSize;   // 菜单项分组字体大小
    private int mItemBackground;    // 菜单项背景颜色
    private int mItemTextColor;     // 菜单项字体颜色
    private float mItemTextSize;    // 菜单项字体大小
    private int mMenuBackground;    // 菜单标题背景
    private int mMenuTextColor;     // 菜单标题字体颜色
    private float mMenuTextSize;    // 菜单标题字体大小
    private String mDefaultMenuName;// 菜单标题默认名称
    private boolean mItemDecoration;// 是否显示分割线

    public RecyclerSideMenuBar(Context context) {
        super(context);
        init(context);
    }

    public RecyclerSideMenuBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RecyclerSideMenuBar);
        mGroupBackground = typedArray.getColor(R.styleable.RecyclerSideMenuBar_group_background, mGroupBackground);
        mGroupTextColor = typedArray.getColor(R.styleable.RecyclerSideMenuBar_group_text_color, mGroupTextColor);
        mGroupTextSize = typedArray.getDimension(R.styleable.RecyclerSideMenuBar_group_text_size, mGroupTextSize);
        mItemBackground = typedArray.getColor(R.styleable.RecyclerSideMenuBar_item_background, mItemBackground);
        mItemTextColor = typedArray.getColor(R.styleable.RecyclerSideMenuBar_item_text_color, mItemTextColor);
        mItemTextSize = typedArray.getDimension(R.styleable.RecyclerSideMenuBar_item_text_size, mItemTextSize);
        mMenuBackground = typedArray.getColor(R.styleable.RecyclerSideMenuBar_menu_background, mMenuBackground);
        mMenuTextColor = typedArray.getColor(R.styleable.RecyclerSideMenuBar_menu_color, mMenuTextColor);
        mMenuTextSize = typedArray.getDimension(R.styleable.RecyclerSideMenuBar_menu_text_size, mMenuTextSize);
        mDefaultMenuName = typedArray.getString(R.styleable.RecyclerSideMenuBar_menu_name);
        mItemDecoration = typedArray.getBoolean(R.styleable.RecyclerSideMenuBar_item_decoration, false);

        init(context);
        typedArray.recycle();
    }


    private void init(Context context) {
        /*LayoutInflater.from(context).inflate(R.layout.layout_recycler_menu, this);
        tvBack = findViewById(R.id.tv_back);
        tvMenuTitle = findViewById(R.id.tv_menu_cate);
        layoutTitle = findViewById(R.id.layout_menu_bar);
        menuList = findViewById(R.id.rcy_menu_item);*/
        menuList.setLayoutManager(new LinearLayoutManager(context));
        menuList.setBackgroundColor(mItemBackground);
        if (mMenuTextColor != 0) {
            tvMenuTitle.setTextColor(mMenuTextColor);
            tvBack.setTextColor(mMenuTextColor);
        }
        if (mMenuTextSize > 0) {
            tvMenuTitle.setTextSize(mMenuTextSize);
            tvBack.setTextSize(mMenuTextSize);
        }
        if (mMenuBackground != 0) {
//            findViewById(R.id.layout_menu_bar).setBackgroundColor(mMenuBackground);
        }

        sideMenuItemList = new ArrayList<>();
        cachedList = new Stack<>();
        cachedTitle = new Stack<>();

        sideMenuSectionAdapter = new SideMenuSectionAdapter(R.layout.item_side_menu, R.layout.item_side_menu, sideMenuItemList);
        sideMenuSectionAdapter.setmHeaderBackgroundColor(mGroupBackground);
        sideMenuSectionAdapter.setmHeaderTextColor(mGroupTextColor);
        sideMenuSectionAdapter.setmHeaderTextSize(mGroupTextSize);
        sideMenuSectionAdapter.setmItemTextColor(mItemTextColor);
        sideMenuSectionAdapter.setmItemTextSize(mItemTextSize);
        sideMenuSectionAdapter.setContext(context);
        menuList.addItemDecoration(new SideMenuItemDecoration());

        menuList.setAdapter(sideMenuSectionAdapter);
        menuList.setItemAnimator(new DefaultItemAnimator());
        if (mItemDecoration) {
            menuList.addItemDecoration(new SideMenuItemDecoration());
        }
        menuList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                curItem = (SideMenuItem) adapter.getItem(position);

                if (itemClickListener != null) {
//                    if (curItem.isChild()) {
//                        itemClickListener.childItemClick(curItem);
//                    } else if (!curItem.isHeader){
//                        itemClickListener.nodeItemClick(curItem);
//                    }

                    if (!curItem.isUser()) {
                        itemClickListener.childItemClick(curItem);
                    } else{
                        itemClickListener.nodeItemClick(curItem);
                    }
                }
            }
        });

        tvBack.setVisibility(View.GONE);
        tvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cachedList.pop();
                cachedTitle.pop();
                show(cachedList.lastElement(), cachedTitle.lastElement());
                // 点击返回按钮，调用返回接口
                if (itemClickListener != null) {
                    itemClickListener.backPressed();
                }
            }
        });
    }

    // 数据展示
    private void show(List<SideMenuItem> dataList, String category) {
        if (cachedTitle.size() <= 1) {
            tvBack.setVisibility(View.GONE);
        } else {
            tvBack.setVisibility(View.VISIBLE);
        }
        if (category == null || category.isEmpty()) {
            tvMenuTitle.setText(mDefaultMenuName);
            layoutTitle.setVisibility(View.GONE);
        } else {
            layoutTitle.setVisibility(View.VISIBLE);
            tvMenuTitle.setText(category);
        }
        sideMenuSectionAdapter.setNewData(dataList);
    }


    public void addChildren(List<SideMenuItem> dataList) {
        String cateName = "";
        if (curItem != null){
            cateName = curItem.getNameStr();
        }
        addChildren(dataList, cateName);
    }

    public void addChildren(List<SideMenuItem> dataList, String cateName) {
        cachedList.push(dataList);
        cachedTitle.push(cateName);
        show(cachedList.lastElement(), cachedTitle.lastElement());
    }

    public void setItemClickListener(MenuItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updateChildren(List<SideMenuItem> dataList) {
        sideMenuSectionAdapter.setNewData(dataList);
    }

    public List<SideMenuItem> getChildren() {
        return sideMenuSectionAdapter.getData();
    }

    /**
     * 菜单栏点击事件接口
     */
    public interface MenuItemClickListener {
        void childItemClick(SideMenuItem t);       // 叶子节点点击，终极菜单
        void nodeItemClick(SideMenuItem t);        // 节点点击，子菜单
        void backPressed();
    }
}
