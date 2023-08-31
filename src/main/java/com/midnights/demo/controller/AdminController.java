package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.admin.*;
import com.midnights.demo.service.AdminService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/*")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/login")
    public String adminPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin){
        return "adminPage";
    }

    @GetMapping("/adminPage")
    public String adminPage(@ModelAttribute Admin admin){
        return "adminPage";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute RequestCreateFestival requestCreateFestival){
        adminService.createFestival(requestCreateFestival);
        return "adminPage";
    }

    @GetMapping("read")
    public String read(Pageable pageable, Model model){
        ResponseReadFestival responseReadFestival = adminService.readFestival(pageable);

        model.addAttribute("response", responseReadFestival);

        return "readView";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute RequestEditFestival requestEditFestival){
        return "editPage";
    }

    @PostMapping("edit/{id}")
    public String update(@ModelAttribute RequestEditFestival requestEditFestival, @PathVariable Long id) {
        adminService.editFestival(requestEditFestival, id);
        return "redirect:/admin/read";
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        adminService.deleteFestival(id);
    }
}
