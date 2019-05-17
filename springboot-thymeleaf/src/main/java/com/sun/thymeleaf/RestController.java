package com.sun.thymeleaf;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @GetMapping("/getHtml")
    public String getHtml(){
        return"<span>123</span>";
    }

}
