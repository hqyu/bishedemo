package com.biye.demo.entity;

public class Car {
    private String id;
    private String openid;
    private String carid;
    private String color;
    private String brand;
    private int carsid;

    public void setCarsid(int carsid) {
        this.carsid = carsid;
    }

    public int getCarsid() {
        return carsid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public String getOpenid() {
        return openid;
    }

    public String getCarid() {
        return carid;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", openid='" + openid + '\'' +
                ", carid='" + carid + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", carsid=" + carsid +
                '}';
    }
}
