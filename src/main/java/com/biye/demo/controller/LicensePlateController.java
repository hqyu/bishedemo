package com.biye.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biye.demo.entity.History;
import com.biye.demo.entity.Message;
import com.biye.demo.entity.TakePhotos;
import com.biye.demo.mapper.ByshejiUserMapper;
import com.biye.demo.mapper.HistoryMapper;
import com.biye.demo.mapper.MessageMapper;
import com.biye.demo.service.BaiduAIService;
import com.biye.demo.utils.Base64Util;
import com.biye.demo.utils.FileUtil;
import com.biye.demo.utils.HttpUtil;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class    LicensePlateController {
    String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
    String accessToken = BaiduAIService.getAcceseeToken();
    static String isAlreadySend="";
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
    int px=1;
    private static boolean isLocked=false;
    @Autowired
    ByshejiUserMapper byshejiUserMapper;

    public static void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    MessageMapper messageMapper;
    @RequestMapping("/cars")
    public String licensePlate() throws Exception {

        MyThread cemera=new MyThread("cemera");

        MyThread2 plate=new MyThread2("plate");
        cemera.start();
        plate.start();
        while(true){

            cemera.sleep(250);
//            System.out.println(2);

            plate.sleep(250);
        }

    }
    private static void lock(){
        while(isLocked);
        setLocked(true);
    }
    private  void unlock(){
        setLocked(false);
    }
    public static void setIsAlreadySend(String isAlreadySend) {
        LicensePlateController.isAlreadySend = isAlreadySend;
    }
    //
    class MyThread extends Thread {  // 继承Thread类，作为线程的实现类
        private String name;       // 表示线程的名称

        public MyThread(String name) {
            this.name = name;      // 通过构造方法配置name属性
        }

        public void run() {  // 覆写run()方法，作为线程 的操作主体
            try {
//                lock();
                TakePhotos.photos();
//                unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ;

    class MyThread2 extends Thread {  // 继承Thread类，作为线程的实现类
        private String name;       // 表示线程的名称

        public MyThread2(String name) {
            this.name = name;      // 通过构造方法配置name属性
        }

        public void run() {  // 覆写run()方法，作为线程 的操作主体
//            lock();
            while (true) {

                String filePath = "D:\\bysheji\\src\\main\\resources\\img\\plate" + px + ".png";
                File f=new File("D:\\bysheji\\src\\main\\resources\\img\\plate" + px + ".png");
                while(!f.exists())//还没有指定文件 等一下
                    f=new File("D:\\bysheji\\src\\main\\resources\\img\\plate" + px + ".png");
                byte[] imgData = new byte[0];
                try {
                    imgData = FileUtil.readFileByBytes(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String imgStr = Base64Util.encode(imgData);
                String imgParam = null;
                try {
                    imgParam = URLEncoder.encode(imgStr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String param = "image=" + imgParam;

                // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。


                String result = null;
                try {
                    result = HttpUtil.post(url, accessToken, param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(result);
                JSONObject jsonObject = JSONObject.parseObject(result);
                Integer isSuccess=jsonObject.getInteger("error_code");
                System.out.println(isSuccess);
                if(isSuccess==null){//识别出有车牌

                    String carId = JSON.parseObject(jsonObject.getString("words_result")).getString("number");
                    String openid;

                    openid = byshejiUserMapper.getOpenIDByCarId(carId);
                    //将车辆进出的结果插入数据库

//                System.out.println(openid);
//                System.out.println(isAlreadySend);
                    if(!isAlreadySend.equals(openid)){
                        History history=new History();
                        int historyid=1+historyMapper.count();
                        history.setHistoryid(historyid+"");
                        history.setId(byshejiUserMapper.getUserIdByOpenid(openid));
                        history.setCarid(carId);
                        history.setTime(dateFormat.format(new Date())+"");
                        history.setComeout("xx停车场");
                        historyMapper.insertHistory(history);
                        System.out.println(isAlreadySend);
                        setIsAlreadySend(openid);
                        System.out.println(isAlreadySend);
                        MessageController mc = new MessageController();
                        int state=mc.sendTemplateMessage2(openid, "您的爱车已进入停车场",carId,"xx停车场");
                        Message message=new Message();
                        message.setOpenid(openid);
                        message.setMessagetext("您的爱车已进入停车场");
                        message.setMessagetime(dateFormat.format(new Date())+"");
                        message.setState(state);
                        int i=1+messageMapper.count();
                        message.setMessageid(i+"");
                        message.setId(byshejiUserMapper.getUserIdByOpenid(openid));
                        messageMapper.insertMessage(message);
                        isAlreadySend=openid;
//                    File f = new File("e:\\bysheji\\src\\main\\resources\\img\\plate" + ex + ".png");
////                    if(!f.exists())break;
//                    f.delete();
//                    ex++;
                    }

                } else{
                    System.out.println("没有识别到车牌");
//                File f = new File("e:\\bysheji\\src\\main\\resources\\img\\plate" + ex + ".png");
////                if(!f.exists())break;
//                f.delete();
//                ex++;
                }

                f.delete();
                px++;
//                unlock();
            }


        }


    }
}
