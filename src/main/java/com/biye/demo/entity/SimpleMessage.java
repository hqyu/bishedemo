package com.biye.demo.entity;

public class SimpleMessage {
    private String openid;
    private String messagetext;

    public String getOpenid() {
        return openid;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "openid='" + openid + '\'' +
                ", messagetext='" + messagetext + '\'' +
                '}';
    }
}
