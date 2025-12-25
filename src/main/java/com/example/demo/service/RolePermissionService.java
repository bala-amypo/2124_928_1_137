package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    // Create role-permission mapping
    RolePermission create(RolePermission rolePermission);

    // ✅ REQUIRED BY TESTS
    RolePermission getMappingById(Long id);

    // ✅ REQUIRED BY TESTS
    List<RolePermission> getPermissionsForRole(Long roleId);

    // Revoke (delete) mapping
    void revokePermission(Long id);
}
