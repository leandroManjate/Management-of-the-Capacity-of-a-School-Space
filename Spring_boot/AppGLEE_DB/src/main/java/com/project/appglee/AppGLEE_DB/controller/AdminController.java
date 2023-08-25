package com.project.appglee.AppGLEE_DB.controller;

import com.project.appglee.AppGLEE_DB.entity.Admin;
import com.project.appglee.AppGLEE_DB.service.AdminService;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public GenericResponse<Admin> login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        return this.adminService.login(email, password);
    }
    @PostMapping
    public GenericResponse save(@RequestBody Admin admin){
        return this.adminService.saveAdmin(admin);
    }
    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @RequestBody Admin admin){
        return this.adminService.saveAdmin(admin);
    }
}
