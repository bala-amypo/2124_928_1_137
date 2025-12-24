package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String fullName;
    private String password;

    private boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserAccount() {}

    /* ---------- getters & setters ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED BY TEST
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {    // REQUIRED BY TEST
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /* ---------- lifecycle ---------- */

    @PrePersist
    public void prePersist() {     // REQUIRED BY TEST
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {      // REQUIRED BY TEST
        updatedAt = LocalDateTime.now();
    }
}
