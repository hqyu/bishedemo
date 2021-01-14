package com.biye.demo.Servlet;

import com.biye.demo.service.WxService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/wx")
public class WxServlet extends HttpServlet {
    private static final long seriaVersionUID= 1L;
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature =request.getParameter("signature");
        String timestamp =request.getParameter("timestamp");
        String nonce =request.getParameter("nonce");
        String echostr=request.getParameter("echostr");
//        System.out.println(signature);
//        System.out.println(timestamp);
//        System.out.println(nonce);
//        System.out.println(echostr);
        //校验请求
        if(WxService.check(timestamp,nonce,signature)){
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.flush();
            out.close();

        }else
        {
            System.out.println("接入失败");
        }
        
    }
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        System.out.println("post");
    }
}
