package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String permissionKey;

    private String description;
    private Boolean active = true;

    public Permission() {}

    public Permission(String permissionKey, String description, Boolean active) {
        this.permissionKey = permissionKey;
        this.description = description;
        this.active = active != null ? active : true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPermissionKey() { return permissionKey; }
    public String getDescription() { return description; }

    public Boolean isActive() { return active; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
