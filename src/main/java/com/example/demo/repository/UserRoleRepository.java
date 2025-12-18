package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.UserRole;

@Repository
public class UserRoleRepository {

    private Map<Long, UserRole> userRoles = new HashMap<>();

    public UserRole save(UserRole ur) {
        userRoles.put(ur.getId(), ur);
        return ur;
    }

    public UserRole findById(Long id) {
        return userRoles.get(id);
    }
}
