package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserAccount user;

    @ManyToOne
    private Role role;

    private LocalDateTime assignedAt;

    public UserRole() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED
        this.id = id;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getAssignedAt() {  // REQUIRED
        return assignedAt;
    }

    @PrePersist
    public void prePersist() {     // REQUIRED
        assignedAt = LocalDateTime.now();
    }
}
