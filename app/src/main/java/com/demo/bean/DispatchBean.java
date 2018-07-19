package com.demo.bean;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/10
 * TODO:
 */
public class DispatchBean {
    private String mTc;

    public DispatchBean() {
    }

    public DispatchBean(String tc) {
        mTc = tc;
    }

    public String getTc() {
        return mTc;
    }

    public void setTc(String tc) {
        mTc = tc;
    }
}
