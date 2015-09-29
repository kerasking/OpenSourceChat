package com.fireant.oschat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 实体类基类
 * Created by zhangdeyi on 15/7/19.
 */
public class BaseBean implements Serializable {

    private int id;

    @XStreamAlias("notice")
    private Notice notice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
