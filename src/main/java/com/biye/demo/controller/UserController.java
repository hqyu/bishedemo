package com.biye.demo.controller;

import com.biye.demo.entity.*;
import com.biye.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
    @Autowired
    ByshejiUserMapper byshejiUserMapper;
    @Autowired
    OperatorMapper operatorMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    HistoryMapper historyMapper;
    @RequestMapping("/user")

    public String userList(Map<String,Object> map) {

        List<ByshejiUser> list = byshejiUserMapper.getUserList();
        map.put("users",list);
        return "user";

    }
    @RequestMapping("/userid")
    public String userId(String id,Map<String,Object>map){
        List<ByshejiUser> list = new ArrayList<ByshejiUser>();
            ByshejiUser user2=byshejiUserMapper.getUserById(id);
            list.add(user2);

            map.put("users",list);



            return "userid";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") String id,Map<String,Object>map){
        List<ByshejiUser> list = new ArrayList<ByshejiUser>();
        ByshejiUser user2=byshejiUserMapper.getUserById(id);
        list.add(user2);

        map.put("user",list);
        Operator operator=new Operator();
        operator.setId(id);
        operator.setOperatorHistory("修改");
        operator.setOperatetime(dateFormat.format(new Date())+"");
        int i=1+operatorMapper.count();
        operator.setOperateid(i+"");
        operatorMapper.insertOperator(operator);//添加对应记录


        return "edituser";
    }
    @RequestMapping("/del/{id}")

    public String delUserList(@PathVariable(value = "id") String id) {

        byshejiUserMapper.delUserById(id);
        List<Car> cars=carMapper.getCarsById(id);
        for(int i=0;i<cars.size();i++)
        carMapper.deleteCarsByCarid(cars.get(i).getCarid());//同步删除车辆表中的信息
        accountMapper.delAccount(id);//删除对应的账户信息
        Operator operator=new Operator();
        operator.setId(id);
        operator.setOperatorHistory("删除");
        operator.setOperatetime(dateFormat.format(new Date())+"");
        int i=1+operatorMapper.count();
        operator.setOperateid(i+"");
        operatorMapper.insertOperator(operator);//添加对应记录
        return "redirect:/user";

    }
    @RequestMapping("/add")
    public String addUserList( ByshejiUser byshejiUser) {
//        System.out.println(1);
        byshejiUser.setCreatetime(dateFormat.format(new Date())+"");
        byshejiUserMapper.addUser(byshejiUser);
        //在操作记录里添加
        Operator operator=new Operator();
        operator.setId(byshejiUser.getId());
        operator.setOperatorHistory("添加");
        operator.setOperatetime(dateFormat.format(new Date())+"");
        int i=1+operatorMapper.count();
        operator.setOperateid(i+"");
        operatorMapper.insertOperator(operator);
        //在车辆信息中添加
        Car car =new Car();
        car.setId(byshejiUser.getId());
        car.setCarid(byshejiUser.getCarid());
        car.setOpenid(byshejiUser.getOpenid());
        car.setBrand("待输入");
        car.setColor("待输入");
        car.setCarsid(carMapper.count());
        carMapper.insertCar(car);
        //在账户信息中添加
        Account2 account=new Account2();
        account.setId(byshejiUser.getId());
        account.setBalance(0);
        accountMapper.insertAccount(account);
//        System.out.println(2);
        return "redirect:/user" ;

    }
    @RequestMapping("/update")
    public String updateUserList( ByshejiUser byshejiUser) {
        byshejiUserMapper.updateUser(byshejiUser);
        Operator operator=new Operator();
        operator.setId(byshejiUser.getId());
        operator.setOperatorHistory("修改");
        operator.setOperatetime(dateFormat.format(new Date())+"");
        operator.setOperateid(operatorMapper.count()+"");
        int i=1+operatorMapper.count();
        operator.setOperateid(i+"");
        operatorMapper.insertOperator(operator);
        return "redirect:/user" ;

    }
    @RequestMapping("/pay")
    public String firstPage(){
        return "pay";
    }
    @RequestMapping("/userlogin")
    public String login(User2 user){
        if(user.getPassword().equals(byshejiUserMapper.isUser(user)))
        {
            String id=user.getId();
            return "redirect:/pay/"+id;

        } else{

            return "userlogin";
        }
    }
    @RequestMapping("/pay/{id}")
    public String payMoney(@PathVariable(value = "id")String id,Map<String,Object>map) throws ParseException {
        List<History> list=historyMapper.getHistoryById(id);
        int i=list.size()-1;
        History history=list.get(i);
        String time1=history.getTime();
        Date date1=dateFormat.parse(time1);
        String str=dateFormat.format(new Date());
        Date date2=dateFormat.parse(str);
        long t1=date1.getTime();
        long t2=date2.getTime();
        int minute = (int) ((t2-t1) / (1000 * 60));
        String minutes=minute+"";
        java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");
        double money1=minute*0.2;
        String money=df.format(money1)+"";
        String carid=byshejiUserMapper.getCaridById(id);
        Pay pay=new Pay();
        pay.setComeTime(time1);
        pay.setOutTime(str);
        pay.setCarid(carid);
        pay.setMinutes(minutes);
        pay.setMoney(money);
        pay.setId(id);
        List<Pay> lists = new ArrayList<Pay>();
        lists.add(pay);
        map.put("pays",lists);
        return "paymoney";
    }
    @RequestMapping("/pay/pays")
    public  String pays(String id,String money){
        Account2 account2=new Account2();
        account2.setId(id);
        System.out.println(money);
        double moneys=Double.valueOf(money);
        System.out.println(moneys);
        account2.setBalance(moneys);
        accountMapper.payAccount(account2);
        Operator operator=new Operator();
        operator.setId(id);
        operator.setOperatorHistory("付款");
        operator.setOperatetime(dateFormat.format(new Date())+"");
        operator.setOperateid(operatorMapper.count()+"");
        int i=1+operatorMapper.count();
        operator.setOperateid(i+"");
        operatorMapper.insertOperator(operator);
        return "success";
    }
//    @RequestMapping("/testmethod")
//    public String tests(String openid){
//        System.out.println(openid);
//        System.out.println(byshejiUserMapper.getUserIdByOpenid(openid));
//        return "tests";
//    }
//    @RequestMapping("tests")
//    public String tes(){
//        return "tests";
//    }
}