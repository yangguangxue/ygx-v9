package com.ygx.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/htmlController")
public class HtmlController {

    @RequestMapping("/htmlTest")
    public String htmlTest(){
        System.out.println("good --**********************************");
        return "hello";
    }
}
