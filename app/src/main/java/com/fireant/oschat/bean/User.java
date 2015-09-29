package com.fireant.oschat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zhangdeyi on 15/7/19.
 */
@XStreamAlias("user")
public class User extends BaseBean {

    @XStreamAlias("uid")
    private int id;

    @XStreamAlias("name")
    private String name;
    @XStreamAlias("portrait")
    private String portrait;
    @XStreamAlias("score")
    private String score;// 积分
    @XStreamAlias("fans")
    private String fans;// 粉丝
    @XStreamAlias("followers")
    private String followers;// 关注
    @XStreamAlias("gender")
    private String gender;
    @XStreamAlias("from")
    private String from;
    @XStreamAlias("expertise")
    private String expertise;// 擅长
    @XStreamAlias("devplatform")
    private String devplatform;// 开发平台
    @XStreamAlias("relation")
    private int relation;
    @XStreamAlias("latestonline")
    private int latestonline;// 最后登录时间
    @XStreamAlias("jointime")
    private String jointime;
    @XStreamAlias("location")
    private String location;
    @XStreamAlias("desc")
    private String desc;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getDevplatform() {
        return devplatform;
    }

    public void setDevplatform(String devplatform) {
        this.devplatform = devplatform;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public int getLatestonline() {
        return latestonline;
    }

    public void setLatestonline(int latestonline) {
        this.latestonline = latestonline;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGenderShow() {
        if (gender.equals("1")) {
            return "男";
        }
        if (gender.equals("男")) {
            return "男";
        }
        return "女";
    }

    public String getRelationShow() {
        String result = "关注Ta";
        switch (relation) {
            case 0:
                result = "关注Ta";
                break;
            case 1:
                result = "取消互粉";
                break;
            case 2:
                result = "取消关注";
                break;
            case 3:
                result = "关注Ta";
                break;
            default:
                result = "关注Ta";
                break;
        }

        return result;
    }
}
