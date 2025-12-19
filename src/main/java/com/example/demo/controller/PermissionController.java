package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService service;

    @GetMapping
    public List<Permission> getAll() {
        return service.getAllPermissions();
    }

    @GetMapping("/{id}")
    public Permission getById(@PathVariable Long id) {
        return service.getPermissionById(id);
    }

    @PostMapping
    public Permission create(@RequestBody Permission permission) {
        return service.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission update(@PathVariable Long id, @RequestBody Permission permission) {
        return service.updatePermission(id, permission);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deletePermission(id);
    }
}
