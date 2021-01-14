package com.biye.demo;

import com.biye.demo.entity.TakePhotos;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;

import javax.swing.*;


@SpringBootApplication
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        builder.headless(false).run(args);
//
//        SpringApplication.run(DemoApplication.class, args);


    }

}
