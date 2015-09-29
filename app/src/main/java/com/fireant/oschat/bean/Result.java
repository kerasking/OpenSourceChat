package com.fireant.oschat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zhangdeyi on 15/7/19.
 */
@XStreamAlias("result")
public class Result extends BaseBean {

    @XStreamAlias("errorCode")
    private int errorCode;
    @XStreamAlias("errorMessage")
    private String errorMessage;

    public boolean ok() {
        return errorCode == 1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
