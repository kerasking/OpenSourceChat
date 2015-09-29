package com.fireant.oschat.bean;

import java.io.Serializable;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public class JsonUser implements Serializable {

    /**
     * name : 火蚁
     * id : 253900
     * portrait :
     */
    private String name;
    private int id;
    private String portrait;
    private String pinyin;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
