package com.biye.demo.controller;

import com.biye.demo.entity.Account;
import com.biye.demo.entity.Account2;
import com.biye.demo.entity.ByshejiUser;
import com.biye.demo.entity.Operator;
import com.biye.demo.mapper.AccountMapper;
import com.biye.demo.mapper.OperatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    OperatorMapper operatorMapper;
    @RequestMapping("/account")
    public String getAccount(Map<String,Object> map){
        List<Account> list = accountMapper.getAccount();
        map.put("accounts",list);
        return "account";
    }
    @RequestMapping("/accountid")
    public String getAccountById(String id,Map<String,Object> map){
        List<Account> list = accountMapper.getAccountById(id);
        map.put("accounts",list);
        return "account";
    }
    @RequestMapping("/updateAccount")
    public String updateAccount(Account account){
        Account2 account2=new Account2();
        System.out.println(account.getBalance());
        account2.setId(account.getId());
        account2.setBalance(Integer.valueOf(account.getBalance()).intValue());
        accountMapper.updateAccount(account2);
        Operator operator=new Operator();
        operator.setId(account.getId());
        operator.setOperatorHistory("充值");
        operator.setOperatetime(dateFormat.format(new Date())+"");
        int i=1+operatorMapper.count();
        operator.setOperateid(i+"");
        operatorMapper.insertOperator(operator);//添加对应记录
        return "redirect:account";
    }
    @RequestMapping("/delAccount/{id}")
    public String delAccount(@PathVariable(value = "id")String id){
        accountMapper.delAccount(id);
        return  "redirect:account";
    }
}
