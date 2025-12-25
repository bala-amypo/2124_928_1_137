package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private String description;

    private boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore   // 🔴 FIX recursion
    private List<UserRole> userRoles;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /* Getters & Setters */

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
