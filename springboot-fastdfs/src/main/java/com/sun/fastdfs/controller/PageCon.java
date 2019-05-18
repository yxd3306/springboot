package com.sun.fastdfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 17:03:01
 * @describe
 */
@Controller
public class PageCon {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
