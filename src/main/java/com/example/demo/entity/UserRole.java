package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
    name = "user_roles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRole {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= RELATIONSHIPS ================= */

    // ✅ EAGER fixes ByteBuddy / Jackson serialization issues
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    // ✅ EAGER fixes ByteBuddy / Jackson serialization issues
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /* ================= AUDIT FIELD ================= */

    @Column(name = "assigned_at", nullable = false, updatable = false)
    private Instant assignedAt;

    /* ================= CONSTRUCTOR ================= */

    public UserRole() {
    }

    /* ================= JPA LIFECYCLE ================= */

    // ✅ REQUIRED BY TESTS
    @PrePersist
    public void prePersist() {
        this.assignedAt = Instant.now();
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    // ✅ REQUIRED BY TESTS
    public Instant getAssignedAt() {
        return assignedAt;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
