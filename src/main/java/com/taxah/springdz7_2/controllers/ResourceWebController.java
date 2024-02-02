package com.taxah.springdz7_2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ResourceWebController {
    @GetMapping("/hello")
    public String hello() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/public-data")
    public String publicResource(){
        return "public.html";
    }

    @GetMapping("/private-data")
    public String privateResource(){
        return "private.html";
    }
}
