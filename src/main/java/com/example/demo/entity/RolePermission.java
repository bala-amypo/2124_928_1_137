package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role_permissions")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Role role;

    @ManyToOne(optional = false)
    private Permission permission;

    public RolePermission() {}

    public Long getId() { return id; }
    public Role getRole() { return role; }
    public Permission getPermission() { return permission; }

    public void setRole(Role role) { this.role = role; }
    public void setPermission(Permission permission) { this.permission = permission; }
}
