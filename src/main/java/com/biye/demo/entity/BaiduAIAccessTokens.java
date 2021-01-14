package com.biye.demo.entity;

public class BaiduAIAccessTokens {
    private String accessToken;
    private Long expireTime;
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken=accessToken;
    }
    public Long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(Long expireTime) {
        this.expireTime=expireTime;
    }
    public BaiduAIAccessTokens(String accessToken, String expireIN) {
        super();
        this.accessToken=accessToken;
        expireTime=System.currentTimeMillis()+Integer.parseInt(expireIN)*1000;

    }
    public boolean isExpired() {
        return System.currentTimeMillis()>expireTime;
    }
}
