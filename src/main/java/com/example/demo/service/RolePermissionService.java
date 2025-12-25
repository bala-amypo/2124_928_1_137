package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    RolePermission create(RolePermission rolePermission);

    RolePermission getById(Long id);

    List<RolePermission> getByRoleId(Long roleId);

    void revokePermission(Long id);
}
