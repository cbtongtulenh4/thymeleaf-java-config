package com.minhphuc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/home")
    public String getHome(){
        return "home";
    }

    @RequestMapping(value = "/login")
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String getAdmin(){
        return "admin";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied(){
        return "redirect:/login?accessDenied";
    }





}
