package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(
    name = "permissions",
    uniqueConstraints = @UniqueConstraint(columnNames = "permission_key")
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= FIELDS ================= */

    @Column(name = "permission_key", nullable = false, unique = true)
    private String permissionKey;

    private String description;

    private boolean active = true;

    /* ================= CONSTRUCTOR ================= */

    public Permission() {
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
