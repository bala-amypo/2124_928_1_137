package com.example.demo.controller;

import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return service.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id,
                                       @RequestBody Permission permission) {
        return service.updatePermission(id, permission);
    }

    @GetMapping("/{id}")
    public Permission getPermission(@PathVariable Long id) {
        return service.getPermissionById(id);
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return service.getAllPermissions();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivatePermission(@PathVariable Long id) {
        service.deactivatePermission(id);
    }
}
