package com.biye.demo.entity;

public class History {
    private String historyid;
    private String id;
    private String time;
    private String carid;
    private String comeout;

    public String getHistoryid() {
        return historyid;
    }

    public String getId() {
        return id;
    }

    public void setHistoryid(String historyid) {
        this.historyid = historyid;
    }

    public String getTime() {
        return time;
    }

    public String getCarid() {
        return carid;
    }

    public String getComeout() {
        return comeout;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public void setComeout(String comeout) {
        this.comeout = comeout;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyid='" + historyid + '\'' +
                ", id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", carid='" + carid + '\'' +
                ", comeout='" + comeout + '\'' +
                '}';
    }
}
