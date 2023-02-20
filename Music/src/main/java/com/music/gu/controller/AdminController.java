package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.request.AdminRequest;
import com.music.gu.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/admin/login")
    public ReturnMessage getLoginStatus(AdminRequest adminRequest, HttpSession session) {
        return adminService.getLoginStatus(adminRequest, session);
    }


}
