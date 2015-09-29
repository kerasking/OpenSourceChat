package com.fireant.oschat.bean;

import java.io.Serializable;

/**
 * 消息
 * Created by zhangdeyi on 15/7/25.
 */
public class Message implements Serializable {

    private int mtype;
    private int btype;
    private JsonUser friend;
    private int id;
    private String body;
    private String create_at;
    private JsonUser send;

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public void setBtype(int btype) {
        this.btype = btype;
    }

    public void setFriend(JsonUser friend) {
        this.friend = friend;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public void setSend(JsonUser send) {
        this.send = send;
    }

    public int getMtype() {
        return mtype;
    }

    public int getBtype() {
        return btype;
    }

    public JsonUser getFriend() {
        return friend;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getCreate_at() {
        return create_at;
    }

    public JsonUser getSend() {
        return send;
    }
}
