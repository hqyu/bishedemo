package com.biye.demo.entity;

public class User2 {
    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User2{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
