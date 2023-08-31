package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.admin.Admin;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.common.ResponseMessage;
import com.midnights.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/*")
public class AdminController {

    private final FestivalService festivalService;

    @GetMapping("/")
    public String adminPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin){
        System.out.println("admin = " + admin);
        return null;
    }
}
