package com.biye.demo.controller;

import com.biye.demo.entity.ByshejiAdmin;
import com.biye.demo.entity.ByshejiAdmin2;
import com.biye.demo.entity.ByshejiUser;
import com.biye.demo.mapper.ByshejiAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    ByshejiAdminMapper byshejiAdminMapper;
    @Autowired
    HttpSession session;


    @RequestMapping("/index")
    public String adminIndex(){
        return "index";
    }
    @RequestMapping("/login")
    public String adminLoginPage(){
        return "login";
    }
    @RequestMapping("/adminLogin")
    public String adminLogin(ByshejiAdmin2 byshejiAdmin, HttpServletRequest request){

            if(byshejiAdmin.getPassword().equals(byshejiAdminMapper.isAdmin(byshejiAdmin)))
            {
                session=request.getSession();
                session.setAttribute("id",byshejiAdmin.getId());
                System.out.println(session.getAttribute("id"));
                return "redirect:/index";

            } else{

                return "login";
            }

    }
    @RequestMapping("/admin")
    public String adminList(Map<String,Object> map){
        List<ByshejiAdmin> list =byshejiAdminMapper.getAdmin();
        map.put("admins",list);
        return "admin";
    }
    @RequestMapping("/adminid")
    public String adminId(String id,Map<String,Object>map){
        List<ByshejiAdmin> list = new ArrayList<ByshejiAdmin>();
        ByshejiAdmin admin2=byshejiAdminMapper.getAdminById(id);
        list.add(admin2);

        map.put("admins",list);



        return "adminid";
    }
    @RequestMapping("/information")
    public String getInformation(Map<String,Object>map){
        String id= (String) session.getAttribute("id");
        List<ByshejiAdmin> list = new ArrayList<ByshejiAdmin>();
        ByshejiAdmin admin2=byshejiAdminMapper.getAdminById(id);
        list.add(admin2);
        map.put("admins",list);
        return "information";
    }

}
