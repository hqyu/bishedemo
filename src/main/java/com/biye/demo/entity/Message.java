package com.biye.demo.entity;

public class Message {
    private String id;
    private String openid;
    private String messagetime;
    private String messagetext;
    private String messageid;
    private  int state;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setMessagetime(String messagetime) {
        this.messagetime = messagetime;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String getId() {
        return id;
    }

    public String getOpenid() {
        return openid;
    }

    public String getMessagetime() {
        return messagetime;
    }

    public String getMessagetext() {
        return messagetext;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", openid='" + openid + '\'' +
                ", messagetime='" + messagetime + '\'' +
                ", messagetext='" + messagetext + '\'' +
                ", messageid='" + messageid + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
