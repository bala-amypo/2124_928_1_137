package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @PostMapping
    public RolePermission assign(@RequestBody RolePermission mapping) {
        return service.assignPermission(mapping);
    }

    @GetMapping("/{id}")
    public RolePermission get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/role/{roleId}")
    public List<RolePermission> list(@PathVariable Long roleId) {
        return service.getByRoleId(roleId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
