package com.example.demo.controller;

import java.util.List;
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

    @PostMapping
    public Role create(@RequestBody Role role) {
        return service.createRole(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        return service.updateRole(id, role);
    }

    @GetMapping("/{id}")
    public Role get(@PathVariable Long id) {
        return service.getRoleById(id);
    }

    @GetMapping
    public List<Role> getAll() {
        return service.getAllRoles();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRole(id);
    }
}
