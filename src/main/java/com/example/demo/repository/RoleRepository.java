package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Role;

@Repository
public class RoleRepository {

    private Map<Long, Role> roles = new HashMap<>();

    public Role save(Role role) {
        roles.put(role.getId(), role);
        return role;
    }

    public Role findById(Long id) {
        return roles.get(id);
    }
}
