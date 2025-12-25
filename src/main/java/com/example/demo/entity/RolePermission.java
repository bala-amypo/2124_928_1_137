package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "role_permissions",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"role_id", "permission_id"}
    )
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RolePermission {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= RELATIONSHIPS ================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    /* ================= AUDIT FIELD ================= */

    @Column(name = "granted_at", nullable = false, updatable = false)
    private LocalDateTime grantedAt;

    /* ================= CONSTRUCTORS ================= */

    public RolePermission() {
    }

    /* ================= JPA LIFECYCLE ================= */

    // ✅ REQUIRED BY TESTS
    @PrePersist
    public void prePersist() {
        this.grantedAt = LocalDateTime.now();
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public Permission getPermission() {
        return permission;
    }

    public LocalDateTime getGrantedAt() {
        return grantedAt;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
