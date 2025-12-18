package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.RolePermission;

@Repository
public class RolePermissionRepository {

    private Map<Long, RolePermission> rolePermissions = new HashMap<>();

    public RolePermission save(RolePermission rp) {
        rolePermissions.put(rp.getId(), rp);
        return rp;
    }

    public RolePermission findById(Long id) {
        return rolePermissions.get(id);
    }
}
