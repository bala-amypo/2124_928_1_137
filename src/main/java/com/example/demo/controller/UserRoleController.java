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

    /* ================= ASSIGN ROLE ================= */

    @PostMapping
    public UserRole assignRole(@RequestBody UserRole userRole) {
        return service.assignRole(userRole);
    }

    /* ================= GET BY ID ================= */

    // ✅ FIXED: matches service + tests
    @GetMapping("/{id}")
    public UserRole getById(@PathVariable Long id) {
        return service.getMappingById(id);
    }

    /* ================= GET BY USER ================= */

    @GetMapping("/user/{userId}")
    public List<UserRole> getRolesForUser(@PathVariable Long userId) {
        return service.getRolesForUser(userId);
    }

    /* ================= DELETE ================= */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeRole(id);
    }
}
