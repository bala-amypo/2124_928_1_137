package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return service.createRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id,
                           @RequestBody Role role) {
        return service.updateRole(id, role);
    }

    @GetMapping("/{id}")
    public Role getRole(@PathVariable Long id) {
        return service.getRoleById(id);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return service.getAllRoles();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateRole(@PathVariable Long id) {
        service.deactivateRole(id);
    }
}
