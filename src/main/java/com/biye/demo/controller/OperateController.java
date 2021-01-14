package com.biye.demo.controller;

import com.biye.demo.entity.History;
import com.biye.demo.entity.Operator;
import com.biye.demo.mapper.OperatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class OperateController {
    @Autowired
    OperatorMapper operatorMapper;
    @RequestMapping("/operate")
    public String getOperate(Map<String,Object>map){
        List<Operator>list=operatorMapper.getOperator();
        map.put("operates",list);
        return "operate";
    }
    @RequestMapping("/operatorid")
    public String getOperateById(String id,Map<String,Object>map){
        List<Operator>list=operatorMapper.getOperatorById(id);
        map.put("operates",list);
        return "operate";
    }
    @RequestMapping("/deloperate/{operateid}")
    public String delOperate(@PathVariable(value = "operateid") String operateid){
        operatorMapper.delOperator(operateid);
        Operator operator=new Operator();
        operator.setId("已删除");
        operator.setOperatetime("已删除");
        operator.setOperatorHistory("已删除");
        operator.setOperateid(operateid);
        operatorMapper.insertOperator(operator);
        return "redirect:/operate";
    }
}
