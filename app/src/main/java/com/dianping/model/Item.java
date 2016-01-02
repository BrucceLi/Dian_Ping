package com.dianping.model;

/**
 * Created by Administrator on 2015/12/30.
 */
public class Item {

    int resId;
    String text;

    public Item(int resId, String text) {
        super();
        this.resId = resId;
        this.text = text;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
