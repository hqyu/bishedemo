package com.biye.demo.entity;

public class ParkCar {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ParkCar{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
