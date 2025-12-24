package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserAccount user;

    @ManyToOne(optional = false)
    private Role role;

    private Instant assignedAt;

    public UserRole() {}

    public UserRole(UserAccount user, Role role) {
        this.user = user;
        this.role = role;
    }

    @PrePersist
    public void onAssign() {
        assignedAt = Instant.now();
    }

    // getters and setters
}
