package com.example.demo.entity;

import jakarta.persistence.*;

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

    public UserRole() {}

    public Long getId() { return id; }
    public UserAccount getUser() { return user; }
    public Role getRole() { return role; }

    public void setUser(UserAccount user) { this.user = user; }
    public void setRole(Role role) { this.role = role; }
}
