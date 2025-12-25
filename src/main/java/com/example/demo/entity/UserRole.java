package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_roles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private LocalDateTime assignedAt;

    @PrePersist
    protected void onAssign() {
        assignedAt = LocalDateTime.now();
    }

    /* Getters & Setters */

    public Long getId() {
        return id;
    }

    public UserAccount getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
