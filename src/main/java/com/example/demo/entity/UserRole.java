package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserAccount user;

    @ManyToOne
    private Role role;

    // ✅ REQUIRED by tests
    public void setUser(UserAccount user) {
        this.user = user;
    }

    // ✅ REQUIRED by tests
    public void setRole(Role role) {
        this.role = role;
    }

    public UserAccount getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }
}
