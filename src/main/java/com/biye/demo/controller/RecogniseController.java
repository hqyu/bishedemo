package com.biye.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecogniseController {
    @RequestMapping("/cemera")
    public String test(){return "cemera";}
}
