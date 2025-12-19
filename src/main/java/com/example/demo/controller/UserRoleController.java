package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService service;

    @PostMapping("/add")
    public UserRole add(@RequestBody UserRole ur) {
        return service.assignRole(ur);
    }

    @GetMapping("/get/{id}")
    public UserRole get(@PathVariable Long id) {
        return service.getMapping(id);
    }
}
