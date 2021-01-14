package com.biye.demo.controller;

import com.biye.demo.entity.History;
import com.biye.demo.entity.Message;
import com.biye.demo.entity.Operator;
import com.biye.demo.entity.SimpleMessage;
import com.biye.demo.mapper.ByshejiUserMapper;
import com.biye.demo.mapper.MessageMapper;
import com.biye.demo.service.WxService;
import com.biye.demo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    //private static final String OPENID="oYhYl1f1Bx16E6gJInoFQpI7irZI";
    //test
    //private static  String[]a= {"oYhYl1almlP5zuYmA040ikVyb8bc","oYhYl1f1Bx16E6gJInoFQpI7irZI","oYhYl1XkR0OBhgG3q4tTmLTGYI4Q","oYhYl1aQgVnR7W0cPUyZlBbNGPjE","oYhYl1Rw1Y2v3mtD1AMUW6TsJ974"};
    //test
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
    @Autowired
    ByshejiUserMapper byshejiUserMapper;
    @Autowired
    MessageMapper messageMapper;
    //    public void set() {
//        String at =WeixinService.getAcceseeToken();
//        String url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+at;
//        String data="{\r\n" +
//                "    \"industry_id1\":\"1\",\r\n" +
//                "    \"industry_id2\":\"4\"\r\n" +
//                "}";
//        String result =Util.post(url, data);
//        System.out.println(result);
//    }
//    public void get() {
//        String at=WeixinService.getAcceseeToken();
//        String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+at;
//        String result=Util.get(url);
//        System.out.println(result);
//    }

    public Integer sendTemplateMessage( String openid,String messagetext) {
        //发送模板消息，a为要发送的openid

        String at= WxService.getAcceseeToken();
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
//        System.out.println(at);
        String data="      {\r\n" +
                "           \"touser\":\""+openid+"\",\r\n" +
                "           \"template_id\":\"1K-UsatqBcrWygARgiZtcV0rhBeoGOPpvCSTY1lXV8c\",\r\n" +
                "           \"data\":{\r\n" +
                "                   \"first\": {\r\n" +
                "                       \"value\":\""+messagetext+"\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   },\r\n" +
                "                   \"time\": {\r\n" +
                "                       \"value\":\""+dateFormat.format(new Date())+"\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   }\r\n" +
                "           }\r\n" +
                "       }";//里面的内容可以改
        String result= Util.post(url, data);
        JSONObject jsonObject = JSONObject.parseObject(result);
//        if(jsonObject.getInteger("errcode")==0){
//            //在message表中插入发送的消息记录
//            Message message=new Message();
//            message.setOpenid(openid);
//            message.setMessagetext(messagetext);
//            message.setMessagetime(dateFormat.format(new Date())+"");
//           try{
////               System.out.println(byshejiUserMapper.getUserIdByOpenid(openid));
//               String id=byshejiUserMapper.getUserIdByOpenid(openid);
//               int i=1+messageMapper.count();
//               message.setMessageid(i+"");
//
//               message.setId(id);
//               messageMapper.insertMessage(message);
//           }catch (NullPointerException e){
//              e.printStackTrace();
//           }
//
//        }
        return jsonObject.getInteger("errcode");

    }
//    @RequestMapping("/agroupsend")
//    public String sendTemplateMessages(String a,String b) {
//        //发送模板消息，a为要发送的openid
//
//        String at= WxService.getAcceseeToken();
//        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
////        System.out.println(at);
//        String data="      {\r\n" +
//                "           \"touser\":\"oZxduwQiOCQbTkqUfssCj_USIdLM\",\r\n" +
//                "           \"template_id\":\"1K-UsatqBcrWygARgiZtcV0rhBeoGOPpvCSTY1lXV8c\",\r\n" +
//                "           \"data\":{\r\n" +
//                "                   \"first\": {\r\n" +
//                "                       \"value\":\""+b+"\",\r\n" +
//                "                       \"color\":\"#173177\"\r\n" +
//                "                   },\r\n" +
//                "                   \"time\": {\r\n" +
//                "                       \"value\":\""+dateFormat.format(new Date())+"\",\r\n" +
//                "                       \"color\":\"#173177\"\r\n" +
//                "                   }\r\n" +
//                "           }\r\n" +
//                "       }";//里面的内容可以改
//        System.out.println(1);
//        String result= Util.post(url, data);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        return "groupsend";
//
//    }
//    @RequestMapping("/groupsend")
//    public List<String> groupSend(List<String> openids , String content) {
//        //参数为openid的数组，群发消息
////        for(int i=0;i<openId.length;i++) {
////            sendTemplateMessage(openId[i],message);
////        }
//        List<String> failIds = new ArrayList<>();
//        for (String openid : openids) {
//            if(sendTemplateMessage(openid,content) != 0){
//                failIds.add(openid);
//            };
//        }
//        return failIds;
//    }
    /**本来想实现群发功能，后来发现没必要，而且群发接口权限很高所以就不做了，但是有接口权限时下面的代码是可以实现群发的**/
//    @RequestMapping(value = "/groupsends",method = RequestMethod.POST)
//    public String groupSend() {
//        //参数为openid的数组，群发消息
//    //        for(int i=0;i<openId.length;i++) {
//    //            sendTemplateMessage(openId[i],message);
//    //        }
//        //test,等车牌识别功能实现后会使用上面注释掉的代码
//        List<String> failIds = new ArrayList<>();
//        List<String> openIds =new ArrayList<>();
//        openIds.add("oZxduwV8zCLgCAVMUIrKkvpsxdXg");
//        openIds.add("oZxduwYz5_dK87-6U_zfCJxt629Q");
//        openIds.add("oZxduwQiOCQbTkqUfssCj_USIdLM");
//        openIds.add("oZxduwbGMH-MB6QQTVOmnUdJYRf4");
//        openIds.add("oZxduwR1u6zIUQirTdDTGtExmL1A");
//        openIds.add("oZxduwU1TvR7moKYZBy5exKyBeBs");//test数据
//        for (String openid : openIds) {
//            if(sendTemplateMessage(openid,"欢迎光临xx停车场") != 0){
//                failIds.add(openid);
//            };
//        }
//        System.out.println(failIds);
//        return "groupsend";
//
//    }
//    @RequestMapping("/groupsend")
//    public String gs(){
//        return  "groupsend";
//    }

    public Integer sendTemplateMessage2( String openid,String messagetext,String carid,String parkcar) {
        //发送模板消息，a为要发送的openid

        String at= WxService.getAcceseeToken();
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
//        System.out.println(at);
//        String data="      {\r\n" +
//                "           \"touser\":\""+openid+"\",\r\n" +
//                "           \"template_id\":\"1K-UsatqBcrWygARgiZtcV0rhBeoGOPpvCSTY1lXV8c\",\r\n" +
//                "           \"data\":{\r\n" +
//                "                   \"first\": {\r\n" +
//                "                       \"value\":\""+messagetext+"\",\r\n" +
//                "                       \"color\":\"#173177\"\r\n" +
//                "                   },\r\n" +
//                "                   \"time\": {\r\n" +
//                "                       \"value\":\""+dateFormat.format(new Date())+"\",\r\n" +
//                "                       \"color\":\"#173177\"\r\n" +
//                "                   }\r\n" +
//                "           }\r\n" +
//                "       }";//里面的内容可以改
        String data="      {\r\n" +
                "           \"touser\":\""+openid+"\",\r\n" +
                "           \"template_id\":\"MYIz-z5g4vuZA1ym74Zax8HSvNJUqNyEe5e3782Nr2I\",\r\n" +
                "           \"url\":\"http://bysheji.gz2vip.idcfengye.com/pay\",\r\n" +
                "           \"data\":{\r\n" +
                "                   \"first\": {\r\n" +
                "                       \"value\":\""+messagetext+"\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   },\r\n" +
                "                   \"carid\": {\r\n" +
                "                       \"value\":\""+carid+"\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   },\r\n" +
                "                   \"parkcar\": {\r\n" +
                "                       \"value\":\""+parkcar+"\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   },\r\n" +
                "                   \"time\": {\r\n" +
                "                       \"value\":\""+dateFormat.format(new Date())+"\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   },\r\n" +
                "                   \"others\": {\r\n" +
                "                       \"value\":\"出场时请及时缴费\",\r\n" +
                "                       \"color\":\"#173177\"\r\n" +
                "                   }\r\n" +
                "           }\r\n" +
                "       }";//里面的内容可以改
        String result= Util.post(url, data);
        JSONObject jsonObject = JSONObject.parseObject(result);
//        if(jsonObject.getInteger("errcode")==0){
//            //在message表中插入发送的消息记录
//            Message message=new Message();
//            message.setOpenid(openid);
//            message.setMessagetext(messagetext);
//            message.setMessagetime(dateFormat.format(new Date())+"");
//           try{
////               System.out.println(byshejiUserMapper.getUserIdByOpenid(openid));
//               String id=byshejiUserMapper.getUserIdByOpenid(openid);
//               int i=1+messageMapper.count();
//               message.setMessageid(i+"");
//
//               message.setId(id);
//               messageMapper.insertMessage(message);
//           }catch (NullPointerException e){
//              e.printStackTrace();
//           }
//
//        }
        System.out.println( jsonObject.getInteger("errcode"));
        return jsonObject.getInteger("errcode");

    }
    @RequestMapping("/messagemange")
    public String messageList(Map<String,Object>map){
        List<Message>list=messageMapper.getMessage();
        map.put("messages",list);
        return "messagemange";
    }
    @RequestMapping("/messagesid")
    public String getMessageById(String id,Map<String,Object>map){
        List<Message>list=messageMapper.getMessageById(id);
        map.put("messages",list);
        return "messagemange";
    }
    @RequestMapping("/delmessage/{messageid}")
    public String delMessage(@PathVariable(value = "messageid") String messageid){
        messageMapper.delMessage(messageid);
        Message message=new Message();
        message.setId("已删除");
        message.setMessagetime("已删除");
        message.setMessagetext("已删除");
        message.setOpenid("已删除");
        int i=messageMapper.count()+1;
        message.setMessageid(i+"");
        message.setState(2);
        messageMapper.insertMessage(message);
        return "redirect:/messagemange";
    }
    @RequestMapping("/sendmessage")
    public String send(SimpleMessage simpleMessage){
        MessageController messageController=new MessageController();
        String openid=simpleMessage.getOpenid();
        String messagetext=simpleMessage.getMessagetext();
        int state=messageController.sendTemplateMessage(openid,messagetext);
        Message message=new Message();
        message.setOpenid(openid);
        message.setMessagetext(messagetext);
        message.setMessagetime(dateFormat.format(new Date())+"");
        message.setState(state);
        int i=1+messageMapper.count();
        message.setMessageid(i+"");
        message.setId(byshejiUserMapper.getUserIdByOpenid(openid));
        messageMapper.insertMessage(message);

        return "redirect:/messagemange";
    }
}

