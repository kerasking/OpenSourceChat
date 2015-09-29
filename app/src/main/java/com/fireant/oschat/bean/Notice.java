package com.fireant.oschat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 通知信息
 * Created by zhangdeyi on 15/7/19.
 */
@XStreamAlias("notice")
public class Notice implements Serializable {

    private int atmeCount;// @我
    private int msgCount;// 留言
    private int reviewCount;// 评论
    private int newFansCount;// 新粉丝
    private int newLikeCount;// 新点赞

    public int getAtmeCount() {
        return atmeCount;
    }

    public void setAtmeCount(int atmeCount) {
        this.atmeCount = atmeCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getNewFansCount() {
        return newFansCount;
    }

    public void setNewFansCount(int newFansCount) {
        this.newFansCount = newFansCount;
    }

    public int getNewLikeCount() {
        return newLikeCount;
    }

    public void setNewLikeCount(int newLikeCount) {
        this.newLikeCount = newLikeCount;
    }
}
