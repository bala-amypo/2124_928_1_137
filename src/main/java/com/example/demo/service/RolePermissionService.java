package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    RolePermission grantPermission(RolePermission rolePermission);

    RolePermission getMappingById(Long id);

    List<RolePermission> getPermissionsForRole(Long roleId);

    void revokePermission(Long id);
}
