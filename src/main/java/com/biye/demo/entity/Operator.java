package com.biye.demo.entity;

public class Operator {
    private String id;
    private String operatorHistory;
    private String operatetime;
    private String operateid;
    public void setId(String id) {
        this.id = id;
    }

    public void setOperatorHistory(String operatorHistory) {
        this.operatorHistory = operatorHistory;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime;
    }

    public String getOperateid() {
        return operateid;
    }

    public void setOperateid(String operateid) {
        this.operateid = operateid;
    }

    public String getId() {
        return id;
    }

    public String getOperatorHistory() {
        return operatorHistory;
    }

    public String getOperatetime() {
        return operatetime;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id='" + id + '\'' +
                ", operatorHistory='" + operatorHistory + '\'' +
                ", operatetime='" + operatetime + '\'' +
                ", operateid='" + operateid + '\'' +
                '}';
    }
}
