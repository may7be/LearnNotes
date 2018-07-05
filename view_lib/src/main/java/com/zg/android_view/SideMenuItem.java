package com.zg.android_view;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 侧边菜单栏项目对象
 *
 * Created by Liu Huanbin
 * 2017/3/14
 */

public class SideMenuItem<T> extends SectionEntity {
    private String objectId;
    private String nameStr;//名字
    private String iconUrl;//图片
    private int childcnt;//列表子项数目
    private boolean isChild;//下级菜单true为有，false为无 /* smartmessage 可删除*/
    private boolean isUser;

    public SideMenuItem(boolean isHeader, String header) {
        super(isHeader, header);
        isChild = false;
    }

    public SideMenuItem(T t) {
        super(t);
        isChild = false;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public int getChildcnt() {
        return childcnt;
    }

    public void setChildcnt(int childcnt) {
        this.childcnt = childcnt;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
