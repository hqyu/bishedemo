package com.biye.demo.entity;

public class Pay {
    private String comeTime;
    private  String outTime;
    private  String minutes;
    private String money;
    private String carid;
    private String id;

    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "comeTime='" + comeTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", minutes='" + minutes + '\'' +
                ", money='" + money + '\'' +
                ", carid='" + carid + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
