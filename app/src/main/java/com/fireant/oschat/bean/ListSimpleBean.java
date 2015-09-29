package com.fireant.oschat.bean;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public class ListSimpleBean {

    private int imgRes;
    private String text;

    public ListSimpleBean(int imgRes, String text) {
        this.imgRes = imgRes;
        this.text = text;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
