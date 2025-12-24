package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;

    private LocalDateTime grantedAt;

    public RolePermission() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public LocalDateTime getGrantedAt() {   // REQUIRED
        return grantedAt;
    }

    @PrePersist
    public void prePersist() {     // REQUIRED
        grantedAt = LocalDateTime.now();
    }
}
