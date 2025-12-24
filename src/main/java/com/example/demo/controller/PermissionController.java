package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public Permission create(@RequestBody Permission permission) {
        return service.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission update(@PathVariable Long id,
                             @RequestBody Permission permission) {
        return service.updatePermission(id, permission);
    }

    @GetMapping("/{id}")
    public Permission get(@PathVariable Long id) {
        return service.getPermissionById(id);
    }

    @GetMapping
    public List<Permission> getAll() {
        return service.getAllPermissions();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivatePermission(id);
    }
}
