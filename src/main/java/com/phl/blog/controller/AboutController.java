package com.phl.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-08-16:32
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        System.out.println("测试");
        return "about";
    }
}
