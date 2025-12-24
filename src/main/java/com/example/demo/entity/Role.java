package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;
    private String description;
    private boolean active = true;

    public Role() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {    // REQUIRED
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
