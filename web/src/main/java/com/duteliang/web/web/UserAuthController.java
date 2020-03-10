package com.duteliang.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/1 22:58
 */
@Controller
@RequestMapping("auth")
public class UserAuthController {

    @GetMapping("login")
    public String login(){
        return "base/login";
    }

    @GetMapping
    public String form(String username,String password){
        return "";
    }



}
