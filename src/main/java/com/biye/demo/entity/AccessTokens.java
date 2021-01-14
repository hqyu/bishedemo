package com.biye.demo.entity;

public class AccessTokens {
    private String accessToken;
    private long expireTime;
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken=accessToken;
    }
    public long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(long expireTime) {
        this.expireTime=expireTime;
    }
    public AccessTokens(String accessToken, String expireIN) {
        super();
        this.accessToken=accessToken;
        expireTime=System.currentTimeMillis()+Integer.parseInt(expireIN)*1000;

    }
    public boolean isExpired() {
        return System.currentTimeMillis()>expireTime;
    }
}
