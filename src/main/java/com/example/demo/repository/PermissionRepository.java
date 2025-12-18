package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Permission;

@Repository
public class PermissionRepository {

    private Map<Long, Permission> permissions = new HashMap<>();

    public Permission save(Permission permission) {
        permissions.put(permission.getId(), permission);
        return permission;
    }

    public Permission findById(Long id) {
        return permissions.get(id);
    }
}
