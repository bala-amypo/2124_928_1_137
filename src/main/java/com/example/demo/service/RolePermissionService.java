package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {
 
    RolePermission create(RolePermission rolePermission);
 
    RolePermission getMappingById(Long id);
 
    List<RolePermission> getPermissionsForRole(Long roleId);
 
    void revokePermission(Long id);
}
