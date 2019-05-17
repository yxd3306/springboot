package com.sun.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("HNS","湖南省");
        model.addAttribute("GDS","广东省");
        model.addAttribute("BJS","北京市");
        model.addAttribute("FJS","福建省");
        return "index";
    }

}
