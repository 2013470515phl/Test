package com.phl.blog.controller.admin;

import com.phl.blog.entity.User;
import com.phl.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-01-16:18
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String LoginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String Login(@RequestParam String userName,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.login(userName, password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误!");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String LoginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }


}
