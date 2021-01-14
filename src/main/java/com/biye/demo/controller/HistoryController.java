package com.biye.demo.controller;

import com.biye.demo.entity.Car;
import com.biye.demo.entity.History;
import com.biye.demo.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HistoryController {
    @Autowired
    HistoryMapper historyMapper;
    @RequestMapping("/history")
    public String getHistory(Map<String,Object>map){
        List<History> list=historyMapper.getHistory();
        map.put("history",list);
        return "history";

    }
    @RequestMapping("/delhistory/{historyid}")
    public String delHistory(@PathVariable(value = "historyid") String historyid){

        historyMapper.delHistory(historyid);
        History history=new History();
        history.setHistoryid(historyid);
        history.setId("已删除");
        history.setComeout("已删除");
        history.setTime("已删除");
        history.setCarid("已删除");
        historyMapper.insertHistory(history);
        return "redirect:/history";
    }
    @RequestMapping("/edithistory")
    public String editHistory(History history){
        historyMapper.editHistory(history);
        return "redirect:/history";
    }
    @RequestMapping("/addhistory")
    public String addHistory(History history){
        int i=1+historyMapper.count();
        history.setHistoryid(i+"");
        historyMapper.insertHistory(history);
        return "history";
    }
    @RequestMapping("/historyid")
    public String getHistoryById(String id,Map<String ,Object>map){
       List<History> list= historyMapper.getHistoryById(id);
       map.put("history",list);
       return "history";
    }
    @RequestMapping("/edithistory/{historyid}")
    public String edit(@PathVariable(value = "historyid") String historyid,Map<String,Object>map){
        List<History> list = new ArrayList<History>();
        History history=historyMapper.getHistoryByHistoryId(historyid);
        list.add(history);
        map.put("user",list);
        return "edithistorys";
    }
}
