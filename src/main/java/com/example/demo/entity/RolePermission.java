package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;

    // ✅ REQUIRED by tests
    public void setRole(Role role) {
        this.role = role;
    }

    // ✅ REQUIRED by tests
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public Permission getPermission() {
        return permission;
    }
}
