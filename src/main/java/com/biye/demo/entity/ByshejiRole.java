package com.biye.demo.entity;

import java.util.Date;

public class ByshejiRole {
    private String id;
    private String openid;
    private String name;
    private String carid;
    private String password;
    private String createtime;

    public void setId(String id){
        this.id=id;
    }

    public  String getId() {
        return this.id;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPassword() {
        return password;
    }

    public String getCreatetime() {
        return createtime;
    }

    public String getName() {
        return this.name;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarid() {
        return this.carid;
    }

}
