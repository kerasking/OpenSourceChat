package com.fireant.oschat.bean;

/**
 * Created by zhangdeyi on 15/7/29.
 */
public class ListLeftRightBean {

    private String left;
    private String right;

    public int getRightImg() {
        return rightImg;
    }

    public void setRightImg(int rightImg) {
        this.rightImg = rightImg;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    private int rightImg;

    public ListLeftRightBean(String left, String right, int rightImg) {
        this.left = left;
        this.right = right;
        this.rightImg = rightImg;
    }

    public ListLeftRightBean(String left, String right) {
        this.left = left;
        this.right = right;
    }
}
