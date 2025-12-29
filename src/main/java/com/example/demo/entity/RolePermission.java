package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
    name = "role_permissions",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"role_id", "permission_id"}
    )
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RolePermission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;


    @Column(name = "granted_at", nullable = false, updatable = false)
    private Instant grantedAt;


    public RolePermission() {
    }
 
    @PrePersist
    public void prePersist() {
        this.grantedAt = Instant.now();
    }
 

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public Permission getPermission() {
        return permission;
    }
 
    public Instant getGrantedAt() {
        return grantedAt;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
