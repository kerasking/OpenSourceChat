package com.fireant.oschat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by zhangdeyi on 15/7/30.
 */
@XStreamAlias("oschina")
public class ActiveList extends BaseBean {

    @XStreamAlias("user")
    private User user;
    @XStreamAlias("activies")
    private List<Active> actives;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Active> getActives() {
        return actives;
    }

    public void setActives(List<Active> actives) {
        this.actives = actives;
    }
}
