package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(
    name = "user_roles",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "role_id"}
    )
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserRole() {}

    public Long getId() {
        return id;
    }

    public UserAccount getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
