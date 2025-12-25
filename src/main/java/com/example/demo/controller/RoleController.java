package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    // ✅ ADMIN only
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Role create(@RequestBody Role role) {
        return service.createRole(role);
    }

    // ✅ ADMIN only
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        return service.updateRole(id, role);
    }

    // ✅ Any authenticated user
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public Role get(@PathVariable Long id) {
        return service.getRoleById(id);
    }

    // ✅ THIS FIXES YOUR 401 PROBLEM
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<Role> getAll() {
        return service.getAllRoles();
    }

    // ✅ ADMIN only
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRole(id);
    }
}
