package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/*")
public class AdminController {
    @GetMapping("")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin){
        System.out.println("----------------");
        System.out.println("admin = " + admin);
        System.out.println("----------------");
        return "redirect:/adminPage";
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "adminPage";
    }
}
