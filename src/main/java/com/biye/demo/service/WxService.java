package com.biye.demo.service;

import com.biye.demo.utils.Util;
import com.biye.demo.entity.AccessTokens;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import net.sf.json.JSONObject;


public class WxService {
    private  static final String TOKEN="abc";
    private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private  static final String APPID="wx002480097f196455";
    private  static final String APPSECRET="d476f58f2ac93796cc66e0194fdb2c3c";
    private static AccessTokens at;

    private static void getToken() {
        String url =GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String tokenStr = Util.get(url);
        net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(tokenStr);
        String token=jsonObject.getString("access_token");
        String expireIN=jsonObject.getString("expires_in");
        at =new AccessTokens(token,expireIN);
    }

    public static String getAcceseeToken() {//获取token
        if(at==null||at.isExpired()) {
            getToken();

        }

        //System.out.print(at.getAccessToken());
        return at.getAccessToken();
    }
    //验证签名
    public static boolean check(String timestamp,String nonce,String signature){
        String[] strs =new String[] {TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        String str=strs[0]+strs[1]+strs[2];
        String mysig =sha1(str);
        System.out.println(mysig);
        System.out.println(signature);
        return mysig.equalsIgnoreCase(signature);
    }
    private static String sha1(String src) {//sha1 加密
        try{
            MessageDigest md =MessageDigest.getInstance("sha1");
            byte[] digest=md.digest(src.getBytes());
            char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb =new StringBuilder();
            for(byte b:digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return null;
    }

}
