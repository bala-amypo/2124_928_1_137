package com.example.demo.controller;

import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService service;

    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    // ✅ Assign role to user
    @PostMapping
    public UserRole assignRole(@RequestBody UserRole userRole) {
        return service.assignRole(userRole);
    }

    // ✅ Get mapping by ID
    @GetMapping("/{id}")
    public UserRole getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ Get roles of a user
    @GetMapping("/user/{userId}")
    public List<UserRole> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    // ✅ Remove role from user
    @DeleteMapping("/{id}")
    public void revokeRole(@PathVariable Long id) {
        service.revokeRole(id);
    }
}
