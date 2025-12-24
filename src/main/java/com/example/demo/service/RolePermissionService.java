package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    RolePermission assignPermission(RolePermission rolePermission);

    List<RolePermission> getPermissionsByRoleId(Long roleId);

    // ✅ REQUIRED by compiler
    void revokePermission(Long rolePermissionId);
}
