package com.biye.demo.service;

import com.biye.demo.entity.AccessTokens;
import com.biye.demo.entity.BaiduAIAccessTokens;
import com.biye.demo.utils.Util;

public class BaiduAIService {

    private  static final String APPKEY="CX4D7ZiUjs3oMQ5FiUExCzLv";
    private  static final String SECRETKEY="dV8mRPggdY0kprkipsXKrOLfuWw5W2Q5";
    private static BaiduAIAccessTokens at;

    private static void getToken() {
        String a;
        String b;
        AuthService as=new AuthService();
        a=as.getAuth(APPKEY,SECRETKEY);
        b=as.getEXPIRE()+"";
        at=new BaiduAIAccessTokens(a,b);


    }

    public static String getAcceseeToken() {//获取token
        if(at==null||at.isExpired()) {
            getToken();

        }

        //System.out.print(at.getAccessToken());
        return at.getAccessToken();
    }
}
